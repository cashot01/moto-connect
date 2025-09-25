package br.com.fiap.motoconnect.repository;

import br.com.fiap.motoconnect.model.Rfid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RfidRepository extends JpaRepository<Rfid, Long> {
}
