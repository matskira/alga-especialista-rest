package br.com.devmpoda.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradaException(String msg) {
        super(msg);
    }

    public EstadoNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de estado com código %d", id));
    }
}
