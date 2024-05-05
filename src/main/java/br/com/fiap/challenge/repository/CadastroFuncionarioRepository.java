package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.CadastroFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroFuncionarioRepository extends JpaRepository<CadastroFuncionario, Long> {
}
