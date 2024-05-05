package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.DesempenhoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesempenhoFinanceiroRepository extends JpaRepository<DesempenhoFinanceiro, Long> {
}
