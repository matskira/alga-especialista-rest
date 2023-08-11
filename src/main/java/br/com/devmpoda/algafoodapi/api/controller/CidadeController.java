package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.exception.NegocioException;
import br.com.devmpoda.algafoodapi.domain.model.Cidade;
import br.com.devmpoda.algafoodapi.domain.repository.CidadeRepository;
import br.com.devmpoda.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cadastroCidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
        try {
            return cadastroCidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
        Cidade cidadeRecuperado = cadastroCidadeService.buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeRecuperado, "id");
        try {
            return cadastroCidadeService.salvar(cidadeRecuperado);
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        cadastroCidadeService.excluir(id);
    }


}
