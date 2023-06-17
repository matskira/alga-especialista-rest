package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teste")
@RestController
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

   /* @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> listarPorNome(@RequestParam String nome){
        return cozinhaRepository.porNome(nome);
    } */
}
