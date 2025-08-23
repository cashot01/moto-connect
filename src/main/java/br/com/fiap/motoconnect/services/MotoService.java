package br.com.fiap.motoconnect.services;

import br.com.fiap.motoconnect.repositories.MotoRepository;
import br.com.fiap.motoconnect.models.Moto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> getAllMotos(){
        return  motoRepository.findAll();
    }

    public Moto save(Moto moto) {
        return motoRepository.save(moto);
    }
}
