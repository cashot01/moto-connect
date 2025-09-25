package br.com.fiap.motoconnect.repository;

import br.com.fiap.motoconnect.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
