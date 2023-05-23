package br.com.devmpoda.algafoodapi.jpa.test.cidade;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.Cidade;
import br.com.devmpoda.algafoodapi.domain.repository.CidadeRepository;
import br.com.devmpoda.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);;

		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);;

		Cidade cidade = new Cidade();
		cidade.setNome("Campinas");
		cidade.setEstado(estadoRepository.porId(1L));


		cidade = cidadeRepository.adicionar(cidade);

		System.out.printf("%d - %s \n", cidade.getId(), cidade.getNome());
	}
}
