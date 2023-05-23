package br.com.devmpoda.algafoodapi.repository;

import br.com.devmpoda.algafoodapi.domain.model.Permissao;
import br.com.devmpoda.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager EManager;

    @Override
    public List<Permissao> todas() {
        return EManager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao porId(Long id) {
        return EManager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public Permissao adicionar(Permissao permissao) {
        return EManager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = porId(permissao.getId());
        EManager.remove(permissao);
    }

}
