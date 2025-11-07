package br.com.fiap.motoconnect.service;

import br.com.fiap.motoconnect.model.StatusMoto;
import br.com.fiap.motoconnect.repository.MotoRepository;
import br.com.fiap.motoconnect.repository.RfidRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final MotoRepository motoRepository;
    private final RfidRepository rfidRepository;

    public DashboardService(MotoRepository motoRepository, RfidRepository rfidRepository) {
        this.motoRepository = motoRepository;
        this.rfidRepository = rfidRepository;
    }

    public Long getTotalMotos() {
        return motoRepository.count();
    }

    public Long getTotalMotosNaoIniciado() {
        return motoRepository.countByStatus(StatusMoto.NAO_INICIADO);
    }

    public Long getTotalMotosManutencao() {
        return motoRepository.countByStatus(StatusMoto.MANUTENCAO);
    }

    public Long getTotalMotosRevisadas() {
        return motoRepository.countByStatus(StatusMoto.REVISADA);
    }

    public Long getTotalRfids() {
        return rfidRepository.count();
    }
}
