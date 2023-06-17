package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {


	//List<Cozinha> porNome(String nome);

}
