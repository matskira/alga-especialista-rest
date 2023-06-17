package br.com.devmpoda.algafoodapi.domain.repository;

import br.com.devmpoda.algafoodapi.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
