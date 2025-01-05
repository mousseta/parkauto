package com.parkauto.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parkauto.rest.entity.Commande;
import com.parkauto.rest.service.CommandeService;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Afficher la liste des commandes
    @GetMapping
    public String listCommandes(Model model) {
        model.addAttribute("commandes", commandeService.getCommandes());
        return "commandes/list";
    }

    // Afficher le formulaire d'ajout
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("commande", new Commande());
        return "commandes/form";
    }

    // Enregistrer une commande
    @PostMapping
    public String saveCommande(@ModelAttribute("commande") Commande commande) {
        commandeService.saveCommande(commande);
        return "redirect:/commandes";
    }

    // Afficher le formulaire de modification
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Commande commande = commandeService.getCommandeById(id);
        model.addAttribute("commande", commande);
        return "commandes/form";
    }

    // Supprimer une commande
    @GetMapping("/delete/{id}")
    public String deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return "redirect:/commandes";
    }
}
