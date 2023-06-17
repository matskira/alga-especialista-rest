package br.com.devmpoda.algafoodapi.domain.repository;

import br.com.devmpoda.algafoodapi.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
}
