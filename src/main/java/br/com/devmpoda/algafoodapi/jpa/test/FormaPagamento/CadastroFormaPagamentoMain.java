package br.com.devmpoda.algafoodapi.jpa.test.FormaPagamento;

import br.com.devmpoda.algafoodapi.AlgafoodApiApplication;
import br.com.devmpoda.algafoodapi.domain.model.FormaPagamento;
import br.com.devmpoda.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args); 
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);;

		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao("Cheque");

		FormaPagamento formaPagamento2 = new FormaPagamento();
		formaPagamento2.setDescricao("Vale Refeição");
		
		formaPagamento = formaPagamentoRepository.adicionar(formaPagamento);
		formaPagamento2 = formaPagamentoRepository.adicionar(formaPagamento2);
		
		System.out.printf("%d - %s \n", formaPagamento.getId(), formaPagamento.getDescricao());
		System.out.printf("%d - %s \n", formaPagamento2.getId(), formaPagamento2.getDescricao());
	}
}
