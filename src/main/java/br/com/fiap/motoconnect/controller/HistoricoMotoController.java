package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.HistoricoMoto;
import br.com.fiap.motoconnect.service.HistoricoMotoService;
import br.com.fiap.motoconnect.service.MotoService;
import br.com.fiap.motoconnect.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/historico")
public class HistoricoMotoController {

    private final HistoricoMotoService historicoMotoService;
    private final HistoricoFormHelper formHelper;

    public HistoricoMotoController(HistoricoMotoService historicoMotoService, MotoService motoService, UsuarioService usuarioService) {
        this.historicoMotoService = historicoMotoService;
        this.formHelper = new HistoricoFormHelper(motoService, usuarioService);
    }

    @GetMapping
    public String listarHistoricos(Model model) {
        model.addAttribute("historicos", historicoMotoService.listarTodos());
        return "historico/lista";
    }

    @GetMapping("/novo")
    public String novoHistorico(Model model) {
        model.addAttribute("historicoMoto", new HistoricoMoto());
        formHelper.addFormAttributes(model);
        return "historico/form";
    }

    @PostMapping
    public String salvarHistorico(@Valid @ModelAttribute HistoricoMoto historicoMoto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            formHelper.addFormAttributes(model);
            return "historico/form";
        }
        historicoMotoService.salvar(historicoMoto);
        redirectAttributes.addFlashAttribute("sucesso", "Histórico de moto cadastrado com sucesso!");
        return "redirect:/historico";
    }

    @GetMapping("/{id}/editar")
    public String editarHistorico(@PathVariable Long id, Model model) {
        historicoMotoService.buscarPorId(id).ifPresent(historicoMoto -> model.addAttribute("historicoMoto", historicoMoto));
        formHelper.addFormAttributes(model);
        return "historico/form";
    }

    @DeleteMapping("/{id}")
    public String excluirHistorico(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        historicoMotoService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Histórico de moto excluído com sucesso!");
        return "redirect:/historico";
    }
}
