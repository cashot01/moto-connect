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
import org.springframework.web.bind.annotation.*;
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

    //@GetMapping("/form")
    //public String form() {
    //    return "usuario/form";
    //}

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo objeto Usuario ao modelo
        return "usuario/form"; // Retorna o template correto
    }

    @PostMapping("/form")
    public String create(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirect ){ //biding

        if (result.hasErrors()) {
            return "usuario/form"; // Retorna o template correto em caso de erro
        }

        var message = messageSource.getMessage("user.create.success", null, LocaleContextHolder.getLocale());
        usuarioService.save(usuario);
        //redirect.addFlashAttribute("message", "Usuario cadastrado com sucesso");
        redirect.addFlashAttribute("message", message);
        return "redirect:/usuario";
    }

    // Endpoint para exibir o formulário de edição (GET /usuario/{id}/update)
    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        var usuario = usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        model.addAttribute("usuario", usuario); // Adiciona o usuário ao modelo
        return "usuario/update"; // Retorna o template de atualização
    }

    // Endpoint para processar a atualização (PUT /usuario/{id}/update)
    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid Usuario usuarioDetails, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "usuario/update"; // Retorna o formulário de atualização em caso de erro de validação
        }

        usuarioService.updateUsuario(id, usuarioDetails); // Atualiza o usuário no banco de dados
        var message = messageSource.getMessage("user.update.success", null, LocaleContextHolder.getLocale());
        redirect.addFlashAttribute("message", message); // Adiciona uma mensagem de sucesso
        return "redirect:/usuario"; // Redireciona para a lista de usuários
    }

    // Endpoint para deletar um usuário (GET /usuario/{id}/delete)
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        usuarioService.deleteUsuario(id); // Deleta o usuário do banco de dados
        var message = messageSource.getMessage("user.delete.success", null, LocaleContextHolder.getLocale());
        redirect.addFlashAttribute("message", message); // Adiciona uma mensagem de sucesso
        return "redirect:/usuario"; // Redireciona para a lista de usuários
    }
}
