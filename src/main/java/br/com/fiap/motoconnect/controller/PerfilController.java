package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.Usuario;
import br.com.fiap.motoconnect.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String visualizarPerfil(Authentication authentication, Model model) {
        // Obtém o email do usuário logado
        String email = authentication.getName();

        // Busca o usuário pelo email
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Adiciona o usuário ao modelo
        model.addAttribute("usuario", usuario);

        return "perfil"; // Retorna o template Thymeleaf "perfil.html"
    }
}
