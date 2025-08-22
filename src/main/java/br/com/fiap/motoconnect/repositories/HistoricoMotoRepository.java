package br.com.fiap.motoconnect.repositories;

import br.com.fiap.motoconnect.models.HistoricoMoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMotoRepository extends JpaRepository<HistoricoMoto,Long> {
}
