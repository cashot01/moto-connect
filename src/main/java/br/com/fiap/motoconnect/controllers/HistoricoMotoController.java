package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.HistoricoMoto;
import br.com.fiap.motoconnect.models.Moto;
import br.com.fiap.motoconnect.services.HistoricoMotoService;
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
@RequestMapping("/moto/historico")
@RequiredArgsConstructor
public class HistoricoMotoController {

    private final HistoricoMotoService historicoMotoService;

    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        var historicoMotos = historicoMotoService.getAllHistoricoMotos();
        model.addAttribute("historicoMotos", historicoMotos);
        return "moto/historico/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("historicoMoto", new HistoricoMoto());
        return "moto/historico/form";
    }

    @PostMapping("/form")
    public String create(@Valid HistoricoMoto historicoMoto, BindingResult result, RedirectAttributes redirect ){ //biding

        if (result.hasErrors()) {
            return "moto/historico/form"; // Retorna o template correto em caso de erro
        }

        var message = messageSource.getMessage("historical.create.success", null, LocaleContextHolder.getLocale());
        historicoMotoService.save(historicoMoto);
        redirect.addFlashAttribute("message", message);
        return "redirect:/moto/historico";
    }
}
