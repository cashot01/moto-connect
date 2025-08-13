package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Usuario;
import br.com.fiap.motoconnect.services.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String index(Model model) {
        var usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "index";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @PostMapping("/form")
    public String create(Usuario usuario, RedirectAttributes redirect) {
        usuarioService.save(usuario);
        redirect.addFlashAttribute("message", "Usuario cadastrado com sucesso");
        return "redirect:/usuario";
    }
}
