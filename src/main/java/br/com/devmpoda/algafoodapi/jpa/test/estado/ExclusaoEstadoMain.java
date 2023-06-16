package br.com.devmpoda.algafoodapi.jpa.test.estado;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.Estado;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);;

		Estado estado = new Estado();
		estado.setId(1L);

		estadoRepository.remover(estado.getId());
	}
}
