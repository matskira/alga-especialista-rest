package br.com.devmpoda.algafoodapi.jpa.test.estado;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);;
		estadoRepository.todas().stream().forEach(estado->{
			System.out.println("\n Número estado: "+estado.getId()+" Nome Estado: "+estado.getNome());
		});
	}
}
