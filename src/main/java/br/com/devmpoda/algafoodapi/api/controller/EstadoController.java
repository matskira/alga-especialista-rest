package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
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
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarEstadoService.salvar(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estadoBody) {
        Optional<Estado> estadoRecuperado = estadoRepository.findById(id);

        if (estadoRecuperado.isPresent()) {
            BeanUtils.copyProperties(estadoBody, estadoRecuperado.get(), "id");
            cadastrarEstadoService.salvar(estadoRecuperado.get());

            return ResponseEntity.ok(estadoRecuperado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> excluir(@PathVariable Long id) {
        try {
            cadastrarEstadoService.excluir(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
