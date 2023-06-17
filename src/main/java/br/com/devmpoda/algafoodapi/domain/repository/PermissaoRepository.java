package br.com.devmpoda.algafoodapi.domain.repository;

import br.com.devmpoda.algafoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
