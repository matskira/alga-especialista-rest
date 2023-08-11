package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.model.Estado;
import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import br.com.devmpoda.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastrarEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estado buscar(@PathVariable Long id) {
        return cadastrarEstadoService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody @Valid Estado estado) {
        return cadastrarEstadoService.salvar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody @Valid Estado estadoBody) {
        Estado estadoRecuperado = cadastrarEstadoService.buscarOuFalhar(id);

        BeanUtils.copyProperties(estadoBody, estadoRecuperado, "id");
        return cadastrarEstadoService.salvar(estadoRecuperado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        cadastrarEstadoService.excluir(id);
    }


}
