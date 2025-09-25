package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.Rfid;
import br.com.fiap.motoconnect.service.RfidService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rfid")
public class RfidController {

    private final RfidService rfidService;

    public RfidController(RfidService rfidService) {
        this.rfidService = rfidService;
    }

    @GetMapping
    public String listarRfids(Model model) {
        model.addAttribute("rfids", rfidService.listarTodos());
        return "rfid/lista";
    }

    @GetMapping("/novo")
    public String novoRfid(Model model) {
        model.addAttribute("rfid", new Rfid());
        return "rfid/form";
    }

    @PostMapping
    public String salvarRfid(@Valid @ModelAttribute Rfid rfid, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "rfid/form";
        }
        rfidService.salvar(rfid);
        redirectAttributes.addFlashAttribute("sucesso", "RFID cadastrado com sucesso!");
        return "redirect:/rfid";
    }

    @GetMapping("/{id}/editar")
    public String editarRfid(@PathVariable Long id, Model model) {
        rfidService.buscarPorId(id).ifPresent(rfid -> model.addAttribute("rfid", rfid));
        return "rfid/form";
    }

    @DeleteMapping("/{id}")
    public String excluirRfid(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        rfidService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "RFID excluído com sucesso!");
        return "redirect:/rfid";
    }
}
