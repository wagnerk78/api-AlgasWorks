package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {
@GetMapping("/proprietarios2")

    public List<Proprietario> lista(){
        var proprietario01 = new Proprietario();
        proprietario01.setId(1L);
        proprietario01.setNome("João");
        proprietario01.setEmail("joão@gmail.com");
        proprietario01.setTelefone("34 99999-1111");

    var proprietario02 = new Proprietario();
        proprietario02.setId(2L);
        proprietario02.setNome("Maria");
        proprietario02.setEmail("maria@gmail.com");
        proprietario02.setTelefone("34 98889-1111");

    return Arrays.asList(proprietario01, proprietario02);




    }
}
