package br.com.fiap.motoconnect.service;

import br.com.fiap.motoconnect.model.Moto;
import br.com.fiap.motoconnect.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> listarTodas() {
        return motoRepository.findAll();
    }

    public Optional<Moto> buscarPorId(Long id) {
        return motoRepository.findById(id);
    }

    public Moto salvar(Moto moto) {
        return motoRepository.save(moto);
    }

    public void excluir(Long id) {
        motoRepository.deleteById(id);
    }
}
