package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {


    @Query("from Cozinha where nome like %:nome%")
    List<Cozinha> consultarPorNome(String nome);

    Optional<Cozinha> findCozinhaByNome(String nome);

    boolean existsByNomeContaining(String nome);
}
