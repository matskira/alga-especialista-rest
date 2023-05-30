package br.com.devmpoda.algafoodapi.domain.service;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Cozinha salvat(Cozinha cozinha) {
        return cozinhaRepository.adicionar(cozinha);
    }
}
