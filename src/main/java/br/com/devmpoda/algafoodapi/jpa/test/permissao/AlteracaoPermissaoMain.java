package br.com.devmpoda.algafoodapi.jpa.test.permissao;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.Permissao;
import br.com.devmpoda.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlteracaoPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

		Permissao permissao = new Permissao();
		permissao.setId(1L);;
		permissao.setNome("ALTERAR_PEDIDOS");
		permissao.setDescricao("Alteração de pedidos");
	
		permissaoRepository.adicionar(permissao);
	}
}
