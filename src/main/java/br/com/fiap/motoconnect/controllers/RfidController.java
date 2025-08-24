package br.com.fiap.motoconnect.controllers;

import br.com.fiap.motoconnect.models.Rfid;
import br.com.fiap.motoconnect.services.RfidService;

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
@RequestMapping("/rfid")
@RequiredArgsConstructor
public class RfidController {
    
    private final RfidService rfidService;

    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        var rfids = rfidService.getAllRfids();
        model.addAttribute("rfids", rfids);
        return "rfid/index";
    }

    //@GetMapping("/form")
    //public String form() {
    //    return "rfid/form";
    //}

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("rfid", new Rfid()); // Adiciona um novo objeto Rfid ao modelo
        return "rfid/form"; // Retorna o template correto
    }

    @PostMapping("/form")
    public String create(@Valid Rfid rfid, BindingResult result, RedirectAttributes redirect ) {

        if (result.hasErrors()) {
            return "rfid/form"; // Retorna o template correto em caso de erro
        }

        var message = messageSource.getMessage("rfid.create.success", null, LocaleContextHolder.getLocale());
        rfidService.save(rfid);
        //redirect.addFlashAttribute("message", "Rfid cadastrado com sucesso");
        redirect.addFlashAttribute("message", message);
        return "redirect:/rfid";
    }
}
