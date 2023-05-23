package br.com.devmpoda.algafoodapi.repository;

import br.com.devmpoda.algafoodapi.domain.model.FormaPagamento;
import br.com.devmpoda.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager EManager;

    @Override
    public List<FormaPagamento> todas() {
        return EManager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento porId(Long id) {
        return EManager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento adicionar(FormaPagamento cozinha) {
        return EManager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = porId(formaPagamento.getId());
        EManager.remove(formaPagamento);
    }

}
