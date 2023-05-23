package br.com.devmpoda.algafoodapi.jpa.test.FormaPagamento;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.FormaPagamento;
import br.com.devmpoda.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);;

		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(3L);
	
		formaPagamentoRepository.remover(formaPagamento);
	}
}
