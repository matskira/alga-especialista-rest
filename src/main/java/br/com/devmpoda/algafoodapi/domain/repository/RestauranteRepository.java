package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;

import br.com.devmpoda.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todas();
	Restaurante porId(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void remover(Restaurante restaurante);
}
