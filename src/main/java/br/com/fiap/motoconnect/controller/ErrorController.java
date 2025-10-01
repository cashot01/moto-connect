package br.com.fiap.motoconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/acesso-negado")
    public String acessoNegado() {

        return "acesso-negado"; // Retorna o template Thymeleaf "acesso-negado.html"
    }
}
