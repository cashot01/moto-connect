package br.com.fiap.motoconnect.interfaces;

import br.com.fiap.motoconnect.models.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
