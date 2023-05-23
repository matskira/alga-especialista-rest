package br.com.devmpoda.algafoodapi.jpa.test.cidade;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.Cidade;
import br.com.devmpoda.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);;

		Cidade	cidade = cidadeRepository.porId(1L);

		cidadeRepository.remover(cidade);
	}
}
