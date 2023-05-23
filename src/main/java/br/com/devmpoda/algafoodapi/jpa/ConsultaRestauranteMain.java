package br.com.devmpoda.algafoodapi.jpa;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);;
		restauranteRepository.todas().stream().forEach(restaurante->{
			System.out.println("\n Número Restaurante: "+restaurante.getId()+" Nome Restaurante: "+restaurante.getNome()
								+" Cozinha: "+restaurante.getCozinha().getNome());
		});
	}
}
