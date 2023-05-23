package br.com.devmpoda.algafoodapi.domain.repository;

import br.com.devmpoda.algafoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

	List<Estado> todas();
	Estado porId(Long id);
	Estado adicionar(Estado estado);
	void remover(Estado estado);
	
}
