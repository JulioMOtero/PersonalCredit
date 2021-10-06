package br.com.otero.personalcredit.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ValorMaxParcela {

    Integer valorInicial;
    Integer valorFinal;
    BigDecimal porcentagem;
}
