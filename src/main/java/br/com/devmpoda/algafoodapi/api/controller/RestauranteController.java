package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import br.com.devmpoda.algafoodapi.domain.repository.RestauranteRepository;
import br.com.devmpoda.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.todas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.porId(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restauranteRepository.porId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRestauranteService.salvar(restaurante));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteRecuperado = restauranteRepository.porId(id);

        if (restauranteRecuperado != null) {
            BeanUtils.copyProperties(restaurante, restauranteRecuperado, "id");
            cadastroRestauranteService.salvar(restauranteRecuperado);

            return ResponseEntity.ok(restauranteRecuperado);
        }
        return ResponseEntity.notFound().build();
    }
}
