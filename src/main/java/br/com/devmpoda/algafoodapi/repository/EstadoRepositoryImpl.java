package br.com.devmpoda.algafoodapi.repository;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Estado;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager EManager;
	
	@Override
	public List<Estado> todas(){
		return EManager.createQuery("from Estado", Estado.class).getResultList();
	}
	
	@Override
	public Estado porId(Long id){
		return EManager.find(Estado.class, id);
	}
	
	@Override
	@Transactional
	public Estado adicionar(Estado estado) {
		return EManager.merge(estado);
	}
	
	@Override
	@Transactional
	public void remover(Long id) {
		Estado estado = porId(id);
		if (estado == null){
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código %d", id));
		}
		EManager.remove(estado);
	}

}
