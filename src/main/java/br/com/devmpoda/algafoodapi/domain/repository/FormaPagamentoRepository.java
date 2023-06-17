package br.com.devmpoda.algafoodapi.domain.repository;

import br.com.devmpoda.algafoodapi.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
}
