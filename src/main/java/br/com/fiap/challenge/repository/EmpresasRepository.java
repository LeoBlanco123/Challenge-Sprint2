package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
}
