package br.com.otero.personalcredit.controller;


import br.com.otero.personalcredit.model.ClienteResponse;
import br.com.otero.personalcredit.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credito")
public class ClienteController {


    @Autowired
     ClienteService clienteService;


    @GetMapping("/{nome}")
    ResponseEntity<ClienteResponse> buscaCliente(@PathVariable String nome){
        ClienteResponse busca = this.clienteService.buscaCliente(nome);

        return ResponseEntity.ok().body(busca);

    }

    }


