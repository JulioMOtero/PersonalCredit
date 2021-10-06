package br.com.otero.personalcredit.controller;


import br.com.otero.personalcredit.model.ClienteEValorPedido;
import br.com.otero.personalcredit.model.ClienteResponse;
import br.com.otero.personalcredit.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/credito")
public class ClienteController {


    @Autowired
    ClienteService clienteService;


    @GetMapping("/{nome}")
    public ResponseEntity<ClienteResponse> buscaCliente(@PathVariable String nome) {
        return ResponseEntity.ok().body(this.clienteService.buscaCliente(nome));

    }

    @GetMapping("/{nome}/{valorPedidoEmprestimo}")
    public ResponseEntity<ClienteEValorPedido> clienteEValorPedido(@PathVariable String nome, @PathVariable BigDecimal valorPedidoEmprestimo) {
        return ResponseEntity.ok(this.clienteService.emprestimo(nome, valorPedidoEmprestimo));
    }

}



