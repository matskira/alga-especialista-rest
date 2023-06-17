package br.com.devmpoda.algafoodapi.jpa;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);;
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setId(1L);
	
		cozinhaRepository.deleteById(cozinha1.getId());
	}
}
