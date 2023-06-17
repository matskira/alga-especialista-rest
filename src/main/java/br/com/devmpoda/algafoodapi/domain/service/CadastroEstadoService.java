package br.com.devmpoda.algafoodapi.domain.service;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.model.Estado;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long id) {
        try {
            estadoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de Estado com código %d", id));
        }catch(DataIntegrityViolationException ex){
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
