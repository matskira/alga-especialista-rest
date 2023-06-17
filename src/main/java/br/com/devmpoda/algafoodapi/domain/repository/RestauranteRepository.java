package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;

import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
