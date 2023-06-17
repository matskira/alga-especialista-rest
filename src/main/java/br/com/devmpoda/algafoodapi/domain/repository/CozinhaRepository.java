package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todas();
	List<Cozinha> porNome(String nome);
	Cozinha porId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Long id);
	
}
