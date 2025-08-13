package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Rfid;
import br.com.fiap.motoconnect.services.RfidService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rfids")
public class RfidController {
    
    private final RfidService rfidService;

    public RfidController(RfidService rfidService) {
        this.rfidService = rfidService;
    }

    @GetMapping
    public String index(Model model) {
        var rfids = rfidService.getAllRfids();
        model.addAttribute("rfids", rfids);
        return "index";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @PostMapping("/form")
    public String create(Rfid rfid, RedirectAttributes redirect) {
        rfidService.save(rfid);
        redirect.addFlashAttribute("message", "Rfid cadastrado com sucesso");
        return "redirect:/rfid";
    }
}
