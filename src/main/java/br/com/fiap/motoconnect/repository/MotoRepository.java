package br.com.fiap.motoconnect.repository;

import br.com.fiap.motoconnect.model.Moto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {

    @EntityGraph(attributePaths = {"usuario", "rfid"})
    Optional<Moto> findById(Long id);
}