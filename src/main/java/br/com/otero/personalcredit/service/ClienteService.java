package br.com.otero.personalcredit.service;

import br.com.otero.personalcredit.model.ClienteEValorPedido;
import br.com.otero.personalcredit.model.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;


@Component
public class ClienteService {

    @Autowired
    FeignClientService feignClientService;

    public ClienteResponse buscaCliente(String cliente) {
        List<ClienteResponse> lista = feignClientService.buscaCliente();


        Optional<ClienteResponse> resultado = lista.stream().filter(item -> item.getNome().equals(cliente)).findFirst();
        if(resultado.isPresent()) {
            obterPorcentagemSalario(resultado.get().getIdade());
        }
        return resultado.orElse(null);

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

        throw new RuntimeException("Idade invalida para emprestimo");
    }

    public BigDecimal valorMaxParcela(BigDecimal salarioCliente) {
//        salarioCliente.setScale(2, RoundingMode.HALF_EVEN);
        if (salarioCliente.longValue() >= 1000.00  && salarioCliente.longValue()<= 2000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.05), new MathContext(2, RoundingMode.HALF_UP));
        }else if(salarioCliente.longValue() >= 2001.00  && salarioCliente.longValue()<= 3000.00) {
            return salarioCliente.multiply(BigDecimal.valueOf(0.10), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 3001.00  && salarioCliente.longValue()<= 4000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.15), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 4001.00  && salarioCliente.longValue()<= 5000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.20), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 5001.00  && salarioCliente.longValue()<= 6000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.25), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 6001.00  && salarioCliente.longValue()<= 7000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.30), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 7001.00  && salarioCliente.longValue()<= 8000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.35), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 8001.00  && salarioCliente.longValue()<= 9000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.40), new MathContext(2, RoundingMode.HALF_UP));
        }else if (salarioCliente.longValue() >= 9001.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.45), new MathContext(2, RoundingMode.HALF_UP));
    }
        throw new RuntimeException("salario invalido");
    }


    public ClienteEValorPedido emprestimo(String nome, Integer valorPedidoEmprestimo) {
        ClienteResponse clienteResponse = buscaCliente(nome);
        validacaoEmprestimo(valorPedidoEmprestimo,clienteResponse.getIdade());

        return ClienteEValorPedido.builder()
                .nome(clienteResponse.getNome())
                .salario(clienteResponse.getSalario())
                .valorPedido(BigDecimal.valueOf(valorPedidoEmprestimo))
                .valorEmprestado(BigDecimal.valueOf(valorPedidoEmprestimo))
                .qtdParcelas()
                .build();
    }
    void validacaoEmprestimo(Integer valorPedidoEmprestimo,Integer idade) {
    if(obterPorcentagemSalario(idade).compareTo(BigDecimal.valueOf(valorPedidoEmprestimo)) > -1){
        throw new IllegalArgumentException("valor pedido maior do que pode ser emprestado");
        }
    }


  /*


  */

}
