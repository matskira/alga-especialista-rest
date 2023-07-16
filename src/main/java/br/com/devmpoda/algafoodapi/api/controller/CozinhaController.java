package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import br.com.devmpoda.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscar(@PathVariable Long id) {
        return cadastroCozinhaService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinhaBody) {
        Cozinha cozinhaRecuperada = cadastroCozinhaService.buscarOuFalhar(id);
        BeanUtils.copyProperties(cozinhaBody, cozinhaRecuperada, "id");
        return cadastroCozinhaService.salvar(cozinhaRecuperada);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cadastroCozinhaService.excluir(id);
    }
}
