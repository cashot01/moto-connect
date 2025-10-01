package br.com.fiap.motoconnect.repository;

import br.com.fiap.motoconnect.model.HistoricoMoto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoMotoRepository extends JpaRepository<HistoricoMoto, Long> {

    @EntityGraph(attributePaths = {"moto", "usuario"})
    List<HistoricoMoto> findAll();

    @EntityGraph(attributePaths = {"moto", "usuario"})
    Optional<HistoricoMoto> findById(Long id);
}