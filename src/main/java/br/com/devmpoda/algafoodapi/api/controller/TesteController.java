package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import br.com.devmpoda.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequestMapping("/teste")
@RestController
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> listarPorNome(@RequestParam String nome) {
        return cozinhaRepository.consultarPorNome(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> listarUmaPorNome(@RequestParam String nome) {
        return cozinhaRepository.findCozinhaByNome(nome);
    }

    @GetMapping("/cozinhas/primeiro")
    public Optional<Cozinha> primeiraCozinha() {
        return cozinhaRepository.buscarPrimeiro();
    }

    @GetMapping("/cozinhas/existe-por-nome")
    public boolean existePorNome(@RequestParam String nome) {
        return cozinhaRepository.existsByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> listarRestaurantePorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome-cozinha")
    public List<Restaurante> listarRestaurantePorNomeCozinhaID(String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNomeECozinha(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restauranteTop2PorNome(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/unico-por-nome")
    public Optional<Restaurante> listarUmPorNome(@RequestParam String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/numero-por-cozinha")
    public int numeroRestaurantesPorCozinha(@RequestParam Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {

        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> primeiroRestaurantes() {

        return restauranteRepository.buscarPrimeiro();
    }
}
