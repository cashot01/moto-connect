package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.model.Moto;
import br.com.fiap.motoconnect.model.ModeloMoto;
import br.com.fiap.motoconnect.model.StatusMoto;
import br.com.fiap.motoconnect.service.MotoService;
import br.com.fiap.motoconnect.service.UsuarioService;
import br.com.fiap.motoconnect.service.RfidService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;
    private final UsuarioService usuarioService;
    private final RfidService rfidService;

    public MotoController(MotoService motoService, UsuarioService usuarioService, RfidService rfidService) {
        this.motoService = motoService;
        this.usuarioService = usuarioService;
        this.rfidService = rfidService;
    }

    @GetMapping
    public String listarMotos(Model model) {
        model.addAttribute("motos", motoService.listarTodas());
        return "motos/lista";
    }

    @GetMapping("/nova")
    public String novaMoto(Model model) {
        model.addAttribute("moto", new Moto());
        model.addAttribute("modelos", ModeloMoto.values());
        model.addAttribute("status", StatusMoto.values());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("rfids", rfidService.listarTodos());
        return "motos/form";
    }

    @PostMapping
    public String salvarMoto(@Valid @ModelAttribute Moto moto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("modelos", ModeloMoto.values());
            model.addAttribute("status", StatusMoto.values());
            model.addAttribute("usuarios", usuarioService.listarTodos());
            model.addAttribute("rfids", rfidService.listarTodos());
            return "motos/form";
        }
        
        motoService.salvar(moto);
        redirectAttributes.addFlashAttribute("sucesso", "Moto cadastrada com sucesso!");
        return "redirect:/motos";
    }

    @GetMapping("/{id}")
    public String visualizarMoto(@PathVariable Long id, Model model) {
        model.addAttribute("moto", motoService.buscarPorId(id));
        return "motos/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editarMoto(@PathVariable Long id, Model model) {
        model.addAttribute("moto", motoService.buscarPorId(id));
        model.addAttribute("modelos", ModeloMoto.values());
        model.addAttribute("status", StatusMoto.values());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("rfids", rfidService.listarTodos());
        return "motos/form";
    }

    @DeleteMapping("/{id}")
    public String excluirMoto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        motoService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Moto excluída com sucesso!");
        return "redirect:/motos";
    }

    @PostMapping("/executar-procedimento")
    public String executarProcedimento(RedirectAttributes redirectAttributes) {
        try {
            String resultado = motoService.executarProcedimentoJson();
            redirectAttributes.addFlashAttribute("sucesso", "Procedimento executado com sucesso!");
            redirectAttributes.addFlashAttribute("resultadoJson", resultado);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao executar procedimento: " + e.getMessage());
        }
        return "redirect:/motos";
    }

    @PostMapping("/executar-somatorio")
    public String executarSomatorio(RedirectAttributes redirectAttributes) {
        try {
            String resultado = motoService.executarFuncaoSomatorioJson();
            redirectAttributes.addFlashAttribute("sucesso", "Relatório de somatório executado com sucesso!");
            redirectAttributes.addFlashAttribute("resultadoJson", resultado);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao executar relatório: " + e.getMessage());
        }
        return "redirect:/motos";
    }
}
