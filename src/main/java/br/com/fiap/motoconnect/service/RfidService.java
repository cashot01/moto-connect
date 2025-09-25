package br.com.fiap.motoconnect.service;

import br.com.fiap.motoconnect.model.Rfid;
import br.com.fiap.motoconnect.repository.RfidRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RfidService {

    private final RfidRepository rfidRepository;

    public RfidService(RfidRepository rfidRepository) {
        this.rfidRepository = rfidRepository;
    }

    public List<Rfid> listarTodos() {
        return rfidRepository.findAll();
    }

    public Optional<Rfid> buscarPorId(Long id) {
        return rfidRepository.findById(id);
    }

    public Rfid salvar(Rfid rfid) {
        return rfidRepository.save(rfid);
    }

    public void excluir(Long id) {
        rfidRepository.deleteById(id);
    }
}
