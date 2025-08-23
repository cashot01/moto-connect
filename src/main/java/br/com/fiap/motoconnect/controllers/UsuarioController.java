package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Usuario;
import br.com.fiap.motoconnect.services.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        var usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario/index";
    }

    @GetMapping("/form")
    public String form() {
        return "usuario/form";
    }

    @PostMapping("/form")
    public String create(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirect ){ //biding

        if(result.hasErrors()) return "form";

        var message = messageSource.getMessage("user.create.success", null, LocaleContextHolder.getLocale());
        usuarioService.save(usuario);
        //redirect.addFlashAttribute("message", "Usuario cadastrado com sucesso");
        redirect.addFlashAttribute("message", message);
        return "redirect:/usuario";
    }
}
