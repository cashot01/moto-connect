package br.com.fiap.motoconnect.services;

import br.com.fiap.motoconnect.models.HistoricoMoto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoMotoService {

    private final HistoricoMotoRepository historicoMotoRepository;

    public HistoricoMotoService(HistoricoMotoRepository historicoMotoRepository) {
        this.historicoMotoRepository = historicoMotoRepository;
    }

    public List<HistoricoMoto> getAllHistoricoMotos(){
        return  historicoMotoRepository.findAll();
    }

    public HistoricoMoto save(HistoricoMoto historicoMoto) {
        return historicoMotoRepository.save(historicoMoto);
    }
}
