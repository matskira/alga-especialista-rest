package br.com.devmpoda.algafoodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager EManager;
	
	@Override
	public List<Cozinha> todas(){
		return EManager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Override
	public Cozinha porId(Long id){
		return EManager.find(Cozinha.class, id);
	}
	
	@Override
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return EManager.merge(cozinha);
	}
	
	@Override
	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = porId(cozinha.getId());
		EManager.remove(cozinha);
	}

}
