package br.com.fiap.motoconnect.service;

import br.com.fiap.motoconnect.model.Moto;
import br.com.fiap.motoconnect.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> listarTodas() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
    }

    public void salvar(Moto moto) {
        motoRepository.save(moto);
    }

    public void excluir(Long id) {
        motoRepository.deleteById(id);
    }

    public String executarProcedimentoJson() {
        return motoRepository.executarProcedimentoJson();
    }
}