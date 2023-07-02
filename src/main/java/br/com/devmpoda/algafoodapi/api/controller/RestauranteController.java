package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import br.com.devmpoda.algafoodapi.domain.repository.RestauranteRepository;
import br.com.devmpoda.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
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
        Optional<Restaurante> restauranteRecuperado = restauranteRepository.findById(id);

        if (restauranteRecuperado.isPresent()) {
            BeanUtils.copyProperties(restaurante, restauranteRecuperado.get(), "id",
                    "formasPagamento", "endereco", "dataCriacao", "dataAtualizacao");
            cadastroRestauranteService.salvar(restauranteRecuperado.get());

            return ResponseEntity.ok(restauranteRecuperado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

        if (restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual.get());
        return atualizar(id, restauranteAtual.get());
    }

    /**
     * Faz a junção dos campos recebidos com o objeto restaurante
     * @param dadosOrigem
     * @param restauranteDestino
     */
    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
        
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
