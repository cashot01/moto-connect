package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.ModeloMoto;
import br.com.fiap.motoconnect.model.StatusMoto;
import br.com.fiap.motoconnect.service.RfidService;
import br.com.fiap.motoconnect.service.UsuarioService;
import org.springframework.ui.Model;

public class MotoFormHelper {

    private final UsuarioService usuarioService;
    private final RfidService rfidService;

    public MotoFormHelper(UsuarioService usuarioService, RfidService rfidService) {
        this.usuarioService = usuarioService;
        this.rfidService = rfidService;
    }

    public void addFormAttributes(Model model) {
        model.addAttribute("modelos", ModeloMoto.values());
        model.addAttribute("status", StatusMoto.values());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("rfids", rfidService.listarTodos());
    }
}
