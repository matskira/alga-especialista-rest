package br.com.devmpoda.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial,BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);

}
