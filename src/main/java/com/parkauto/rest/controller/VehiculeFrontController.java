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
import com.parkauto.rest.entity.Location;
import com.parkauto.rest.entity.Vehicule;
import com.parkauto.rest.service.CommandeService;
import com.parkauto.rest.service.LocationService;
import com.parkauto.rest.service.VehiculeService;

@Controller
@RequestMapping("/vehicules")
public class VehiculeFrontController {
    
    @Autowired
    private VehiculeService vehiculeService;

     
    @Autowired
    private CommandeService commandeService;

    @Autowired
    private LocationService locationService;
    
    @GetMapping
    public String getAllVehicules(Model model) {
        model.addAttribute("vehicules", vehiculeService.getVehicules());
        return "vehicules/list";
    }
    
    
    @GetMapping("/new")
    public String createVehiculeForm(Model model) {
        model.addAttribute("vehicule", new Vehicule()); // Ajout d'un objet vide au modèle
        return "vehicules/form";
    }
    
    @PostMapping
    public String saveVehicule(@ModelAttribute("vehicule") Vehicule vehicule) {
        vehiculeService.saveVehicule(vehicule);
        return "redirect:/vehicules";
    }
    
    @GetMapping("/edit/{id}")
    public String editVehiculeForm(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id);
        if (vehicule == null) {
            throw new IllegalArgumentException("Invalid vehicle ID: " + id);
        }
        model.addAttribute("vehicule", vehicule);
        return "vehicules/form";
    }
    
    @PostMapping("/{id}")
    public String updateVehicule(@PathVariable Long id, @ModelAttribute Vehicule vehicule) {
        vehicule.setMatricule(id);
        vehiculeService.saveVehicule(vehicule);
        return "redirect:/vehicules";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteVehicule(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id);
        vehiculeService.deleteVehicule(vehicule);
        return "redirect:/vehicules";
    }
    @GetMapping("/commandes/new")
public String createCommandeForm(Model model) {
    Commande commande = new Commande();
    model.addAttribute("commande", commande);
    model.addAttribute("vehicules", vehiculeService.getVehicules()); // Liste des véhicules pour choisir
    return "commandes/form";
}
@PostMapping("/commandes")
public String saveCommande(@ModelAttribute Commande commande) {
    commandeService.saveCommande(commande);
    return "redirect:/commandes";
}
@GetMapping("/locations/new")
public String createLocationForm(Model model) {
    Location location = new Location();
    model.addAttribute("location", location);
    model.addAttribute("vehicules", vehiculeService.getVehicules());
    return "locations/form";
}
@PostMapping("/locations")
public String saveLocation(@ModelAttribute Location location) {
    locationService.saveLocation(location);
    return "redirect:/locations";
}


}

