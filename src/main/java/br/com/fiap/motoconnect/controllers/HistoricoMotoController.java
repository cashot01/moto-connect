package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.HistoricoMoto;
import br.com.fiap.motoconnect.services.HistoricoMotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/moto/historico")
public class HistoricoMotoController {

    private final HistoricoMotoService historicoMotoService;

    public HistoricoMotoController(HistoricoMotoService historicoMotoService) {
        this.historicoMotoService = historicoMotoService;
    }

    @GetMapping
    public String index(Model model) {
        var historicoMotos = historicoMotoService.getAllHistoricoMotos();
        model.addAttribute("historicoMotos", historicoMotos);
        return "historicoMoto/index";
    }

    @GetMapping("/form")
    public String form() {
        return "historicoMoto/form";
    }

    @PostMapping("/form")
    public String create(HistoricoMoto historicoMoto, RedirectAttributes redirect) {
        historicoMotoService.save(historicoMoto);
        redirect.addFlashAttribute("message", "HistoricoMoto cadastrado com sucesso");
        return "redirect:/historicoMoto";
    }
}
