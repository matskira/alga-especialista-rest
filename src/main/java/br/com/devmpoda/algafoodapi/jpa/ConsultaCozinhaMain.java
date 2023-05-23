package br.com.devmpoda.algafoodapi.jpa;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);;
		cozinhaRepository.todas().stream().forEach(cozinha->{
			System.out.println("\n Número cozinha: "+cozinha.getId()+" Nome Cozinha: "+cozinha.getNome());
		});
	}
}
