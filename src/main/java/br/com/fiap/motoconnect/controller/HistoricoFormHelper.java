package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.service.MotoService;
import br.com.fiap.motoconnect.service.UsuarioService;
import org.springframework.ui.Model;

public class HistoricoFormHelper {

    private final MotoService motoService;
    private final UsuarioService usuarioService;

    public HistoricoFormHelper(MotoService motoService, UsuarioService usuarioService) {
        this.motoService = motoService;
        this.usuarioService = usuarioService;
    }

    public void addFormAttributes(Model model) {
        model.addAttribute("motos", motoService.listarTodas());
        model.addAttribute("usuarios", usuarioService.listarTodos());
    }
}
