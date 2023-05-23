package br.com.devmpoda.algafoodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmpoda.algafoodapi.domain.model.Restaurante;
import br.com.devmpoda.algafoodapi.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager EManager;
	
	@Override
	public List<Restaurante> todas() {
		return EManager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante porId(Long id) {
		return EManager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return EManager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = porId(restaurante.getId());
		EManager.remove(restaurante);
		
	}

	
}
