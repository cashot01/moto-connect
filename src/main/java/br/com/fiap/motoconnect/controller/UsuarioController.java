package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.Usuario;
import br.com.fiap.motoconnect.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Garante que apenas ADMINs podem acessar esta rota
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Retorna o template Thymeleaf "usuarios.html"
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado"; // Retorna o template Thymeleaf "acesso-negado.html"
    }
}
