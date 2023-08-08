package br.com.devmpoda.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    PROPRIEDADES_INVALIDAS("/propriedades-invalidas", "Propriedades inválidas"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrada", "Recurso não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    ERRO_DE_SISTEMA("/erro-sistema ", "Erro de sistema");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://devmpoda.com.br" + path;
        this.title = title;
    }
}
