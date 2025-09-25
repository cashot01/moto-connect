package br.com.fiap.motoconnect.service;

import br.com.fiap.motoconnect.model.HistoricoMoto;
import br.com.fiap.motoconnect.repository.HistoricoMotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoMotoService {

    private final HistoricoMotoRepository historicoMotoRepository;

    public HistoricoMotoService(HistoricoMotoRepository historicoMotoRepository) {
        this.historicoMotoRepository = historicoMotoRepository;
    }

    public List<HistoricoMoto> listarTodos() {
        return historicoMotoRepository.findAll();
    }

    public Optional<HistoricoMoto> buscarPorId(Long id) {
        return historicoMotoRepository.findById(id);
    }

    public HistoricoMoto salvar(HistoricoMoto historicoMoto) {
        return historicoMotoRepository.save(historicoMoto);
    }

    public void excluir(Long id) {
        historicoMotoRepository.deleteById(id);
    }
}
