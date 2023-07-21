package br.com.devmpoda.algafoodapi.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradaException(String msg) {
        super(msg);
    }

    public RestauranteNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d", id));
    }
}
