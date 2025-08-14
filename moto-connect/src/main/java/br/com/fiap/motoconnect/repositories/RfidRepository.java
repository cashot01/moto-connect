package br.com.fiap.motoconnect.repositories;

import br.com.fiap.motoconnect.models.Rfid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RfidRepository extends JpaRepository<Rfid, Long> {
}
