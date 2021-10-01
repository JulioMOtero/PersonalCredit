package br.com.otero.personalcredit.service;


import br.com.otero.personalcredit.model.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "FeignClientService", url = "http://www.mocky.io/v2")
public interface FeignClientService {


    @GetMapping("/5e5ec624310000b738afd85a")
    List<ClienteResponse> buscaCliente();
}
