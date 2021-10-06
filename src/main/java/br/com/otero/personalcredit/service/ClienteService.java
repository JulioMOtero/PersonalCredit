package br.com.otero.personalcredit.service;

import br.com.otero.personalcredit.exception.PessoaNaoEncontradaException;
import br.com.otero.personalcredit.exception.IdadeException;
import br.com.otero.personalcredit.exception.SalarioException;
import br.com.otero.personalcredit.model.ClienteEValorPedido;
import br.com.otero.personalcredit.model.ClienteResponse;
import br.com.otero.personalcredit.model.ValorMaxParcela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static java.math.RoundingMode.UP;


@Component
public class ClienteService {

    @Autowired
    FeignClientService feignClientService;

   private static final List<ValorMaxParcela> regraComprometimentoSalario = new ArrayList<>();
    static {
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(1000)
                .valorFinal(2000)
                .porcentagem(BigDecimal.valueOf(0.05)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(2001)
                .valorFinal(3000)
                .porcentagem(BigDecimal.valueOf(0.10)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(3001)
                .valorFinal(4000)
                .porcentagem(BigDecimal.valueOf(0.15)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(4001)
                .valorFinal(5000)
                .porcentagem(BigDecimal.valueOf(0.20)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(5001)
                .valorFinal(6000)
                .porcentagem(BigDecimal.valueOf(0.25)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(6001)
                .valorFinal(7000)
                .porcentagem(BigDecimal.valueOf(0.30)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(7001)
                .valorFinal(8000)
                .porcentagem(BigDecimal.valueOf(0.35)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(8001)
                .valorFinal(9000)
                .porcentagem(BigDecimal.valueOf(0.40)).build());
        regraComprometimentoSalario.add(ValorMaxParcela.builder()
                .valorInicial(9001)
                .valorFinal(null)
                .porcentagem(BigDecimal.valueOf(0.45)).build());

    }
    public ClienteResponse buscaCliente(String cliente) {
        List<ClienteResponse> lista = feignClientService.buscaCliente();


        Optional<ClienteResponse> resultado = lista.stream().filter(item -> item.getNome().equals(cliente)).findFirst();
        resultado.ifPresent(clienteResponse -> obterPorcentagemSalario(clienteResponse.getIdade()));
        return resultado.orElseThrow(() -> new PessoaNaoEncontradaException("Nome não encontrado"));


    }

    public ClienteEValorPedido emprestimo(String nome, BigDecimal valorPedidoEmprestimo) {
        ClienteResponse clienteResponse = buscaCliente(nome);
        validacaoEmprestimo(valorPedidoEmprestimo, clienteResponse);

        return ClienteEValorPedido.builder()
                .nome(clienteResponse.getNome())
                .salario(clienteResponse.getSalario())
                .valorPedido(valorPedidoEmprestimo)
                .valorEmprestado(valorPedidoEmprestimo)
                .qtdParcelas(calculaQuantidadeParcelas(valorPedidoEmprestimo, clienteResponse))
                .valorDasParcelas(calculaValorParcela(valorPedidoEmprestimo, clienteResponse))
                .build();
    }

    public BigDecimal obterPorcentagemSalario(Integer idadeCliente) {

        if (idadeCliente >= 20 && idadeCliente <= 29) {
            return BigDecimal.valueOf(1.00);
        } else if (idadeCliente >= 30 && idadeCliente <= 49) {
            return BigDecimal.valueOf(0.90);
        } else if (idadeCliente >= 50 && idadeCliente <= 79) {
            return BigDecimal.valueOf(0.70);
        } else if (idadeCliente >= 80) {
            return BigDecimal.valueOf(0.20);
        }
        throw new IdadeException("Idade invalida para emprestimo");
    }

    public BigDecimal valorMaxParcela(BigDecimal salarioCliente) {

        ValorMaxParcela regraEscolhida = regraComprometimentoSalario.stream()
                .filter(regra -> salarioCliente.longValue() >= regra.getValorInicial() && salarioCliente.longValue() <= regra.getValorFinal())
                .findFirst()
                .orElseThrow(() -> new SalarioException("salario invalido"));
            return regraEscolhida.getPorcentagem();
    }

    void validacaoEmprestimo(BigDecimal valorPedidoEmprestimo, ClienteResponse cliente) {
        BigDecimal resultado = cliente.getSalario().multiply(obterPorcentagemSalario(cliente.getIdade()));
        if (resultado.compareTo(cliente.getSalario()) > 0 || cliente.getSalario().compareTo(valorPedidoEmprestimo) < 0) {
            throw new IllegalArgumentException("valor pedido não pode ser emprestado");
        }

    }

    public Integer calculaQuantidadeParcelas(BigDecimal valorEmprestado, ClienteResponse cliente) {
        validacaoEmprestimo(valorEmprestado, cliente);
        BigDecimal valorMaxParcela = valorMaxParcela(cliente.getSalario());
        return valorEmprestado.divide(valorMaxParcela, UP).intValue();
    }


    public BigDecimal calculaValorParcela(BigDecimal valorEmprestado, ClienteResponse cliente) {
        calculaQuantidadeParcelas(valorEmprestado, cliente);
        validacaoEmprestimo(valorEmprestado, cliente);
        return valorEmprestado.divide(new BigDecimal(calculaQuantidadeParcelas(valorEmprestado, cliente)), UP);
    }

}
