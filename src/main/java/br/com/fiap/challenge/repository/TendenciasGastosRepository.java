package br.com.fiap.challenge.repository;


import br.com.fiap.challenge.entity.TendenciasGastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TendenciasGastosRepository extends JpaRepository<TendenciasGastos, Long> {
}
