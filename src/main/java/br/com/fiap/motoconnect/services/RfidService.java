package br.com.fiap.motoconnect.services;

import br.com.fiap.motoconnect.repositories.RfidRepository;
import br.com.fiap.motoconnect.models.Rfid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RfidService {

    private final RfidRepository rfidRepository;


    public RfidService(RfidRepository rfidRepository) {
        this.rfidRepository = rfidRepository;
    }

    public List<Rfid> getAllRfids(){
        return rfidRepository.findAll();
    }

    public Rfid save(Rfid rfid){
        return rfidRepository.save(rfid);
    }
}
