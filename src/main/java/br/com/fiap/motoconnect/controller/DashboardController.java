package br.com.fiap.motoconnect.controller;

import br.com.fiap.motoconnect.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalMotos", dashboardService.getTotalMotos());
        model.addAttribute("totalNaoIniciado", dashboardService.getTotalMotosNaoIniciado());
        model.addAttribute("totalManutencao", dashboardService.getTotalMotosManutencao());
        model.addAttribute("totalRevisadas", dashboardService.getTotalMotosRevisadas());
        model.addAttribute("totalRfids", dashboardService.getTotalRfids());

        return "dashboard";
    }
}