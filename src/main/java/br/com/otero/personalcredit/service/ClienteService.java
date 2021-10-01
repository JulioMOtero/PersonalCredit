package br.com.otero.personalcredit.service;

import br.com.otero.personalcredit.model.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class ClienteService {

  @Autowired
  FeignClientService feignClientService;

  public ClienteResponse  buscaCliente(String cliente){
    List<ClienteResponse> lista = feignClientService.buscaCliente();


    Optional<ClienteResponse> resultado = lista.stream().filter(item -> item.getNome().equals(cliente)).findFirst();


    return resultado.orElse(null);

  }


}
