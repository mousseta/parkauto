package com.parkauto.rest.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("username", "Utilisateur connecté"); // Ajoutez des informations de l'utilisateur si nécessaire
        return "dashboard";
    }
}
