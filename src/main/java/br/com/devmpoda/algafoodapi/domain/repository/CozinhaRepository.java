package br.com.devmpoda.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {


    List<Cozinha> findByNomeContaining(String nome);

    Optional<Cozinha> findCozinhaByNome(String nome);

    boolean existsByNomeContaining(String nome);
}
