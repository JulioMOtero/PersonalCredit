package br.com.otero.personalcredit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClienteEValorPedido {
    @JsonProperty("Nome")
    private String nome;
    @JsonProperty("Salario")
    private BigDecimal salario;
    private BigDecimal valorPedido;
    private BigDecimal valorEmprestado;
    private Integer qtdParcelas;
    private BigDecimal valorDasParcelas;

}
