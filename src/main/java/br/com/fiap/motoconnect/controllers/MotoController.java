package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Moto;
import br.com.fiap.motoconnect.services.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public String index(Model model) {
        var motos = motoService.getAllMotos();
        model.addAttribute("motos", motos);
        return "moto/index";
    }

    @GetMapping("/form")
    public String form(){
        return "moto/form";
    }

    @PostMapping("/form")
    public String create(Moto moto, RedirectAttributes redirect ){ //session
        motoService.save(moto);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso!");
        return "redirect:/moto"; //301
    }
}
