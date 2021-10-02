package br.com.otero.personalcredit.service;

import br.com.otero.personalcredit.model.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        if (salarioCliente.longValue() >= 1000.00  && salarioCliente.longValue()<= 2000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.05));
        }else if(salarioCliente.longValue() >= 2001.00  && salarioCliente.longValue()<= 3000.00) {
            return salarioCliente.multiply(BigDecimal.valueOf(0.10));
        }else if (salarioCliente.longValue() >= 3001.00  && salarioCliente.longValue()<= 4000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.15));
        }else if (salarioCliente.longValue() >= 4001.00  && salarioCliente.longValue()<= 5000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.20));
        }else if (salarioCliente.longValue() >= 5001.00  && salarioCliente.longValue()<= 6000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.25));
        }else if (salarioCliente.longValue() >= 6001.00  && salarioCliente.longValue()<= 7000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.30));
        }else if (salarioCliente.longValue() >= 7001.00  && salarioCliente.longValue()<= 8000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.35));
        }else if (salarioCliente.longValue() >= 8001.00  && salarioCliente.longValue()<= 9000.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.40));
        }else if (salarioCliente.longValue() >= 9001.00){
            return salarioCliente.multiply(BigDecimal.valueOf(0.45));
    }
        throw new RuntimeException("salario invalido");
    }


  /*

  Dependendo da faixa salarial da pessoa a parcela poderá comprometer apenas uma porcentagem do salário dela

De 1000 a 2000 reais, a parcela poderá comprometer 5% do salário
De 2001 a 3000 reais, a parcela poderá comprometer 10% do salário
De 3001 a 4000 reais, a parcela poderá comprometer 15% do salário
De 4001 a 5000 reais, a parcela poderá comprometer 20% do salário
De 5001 a 6000 reais, a parcela poderá comprometer 25% do salário
De 6001 a 7000 reais, a parcela poderá comprometer 30% do salário
De 7001 a 8000 reais, a parcela poderá comprometer 35% do salário
De 8001 a 9000 reais, a parcela poderá comprometer 40% do salário
A partir de 9001 reais, a parcela poderá comprometer 45% do salário

  */

}
