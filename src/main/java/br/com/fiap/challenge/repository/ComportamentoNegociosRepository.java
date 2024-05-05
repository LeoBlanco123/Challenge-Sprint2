package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.ComportamentoNegocios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComportamentoNegociosRepository extends JpaRepository<ComportamentoNegocios, Long> {
}
