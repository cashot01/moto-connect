package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Moto;
import br.com.fiap.motoconnect.models.Usuario;
import br.com.fiap.motoconnect.services.MotoService;

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
@RequestMapping("/moto")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;

    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        var motos = motoService.getAllMotos();
        model.addAttribute("motos", motos);
        return "moto/index";
    }

    //@GetMapping("/form")
    //public String form() {
    //    return "moto/form";
    //}

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("moto", new Moto()); // Adiciona um novo objeto Moto ao modelo
        return "moto/form"; // Retorna o template correto
    }

    @PostMapping("/form")
    public String create(@Valid Moto moto, BindingResult result, RedirectAttributes redirect ){ //biding

        if (result.hasErrors()) {
            return "moto/form"; // Retorna o template correto em caso de erro
        }

        var message = messageSource.getMessage("motorcycle.create.success", null, LocaleContextHolder.getLocale());
        motoService.save(moto);
        //redirect.addFlashAttribute("message", "Moto cadastrado com sucesso");
        redirect.addFlashAttribute("message", message);
        return "redirect:/moto";
    }
}
