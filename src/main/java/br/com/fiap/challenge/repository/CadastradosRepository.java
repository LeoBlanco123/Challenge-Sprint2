package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Cadastrados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastradosRepository extends JpaRepository<Cadastrados, Long> {
}
