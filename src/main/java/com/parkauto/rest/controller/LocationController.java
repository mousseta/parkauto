package com.parkauto.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parkauto.rest.entity.Location;
import com.parkauto.rest.service.LocationService;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public String listLocations(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations); // Ajoute les locations au modèle
        return "locations/list"; // Renvoie vers la vue liste
    }

    @GetMapping("/new")
    public String createLocationForm(Model model) {
        model.addAttribute("location", new Location()); // Ajouter un objet Location vide
        return "locations/form"; // Nom de la vue Thymeleaf
    }



    @GetMapping("/edit/{id}")
public String editLocationForm(@PathVariable Long id, Model model) {
    Location location = locationService.findById(id); // Trouver la location par son ID
    if (location == null) {
        throw new IllegalArgumentException("Invalid location ID: " + id);
    }
    model.addAttribute("location", location); // Ajouter la location au modèle
    return "locations/form"; // Réutiliser le formulaire d'ajout pour l'édition
}
@PostMapping("/edit/{id}")
public String updateLocation(@PathVariable Long id, @ModelAttribute("location") Location location, BindingResult result) {
    if (result.hasErrors()) {
        return "locations/form"; // Retourne le formulaire en cas d'erreurs
    }

    // Vérifier si l'entité existe
    Location existingLocation = locationService.findById(id);
    if (existingLocation == null) {
        throw new IllegalArgumentException("Invalid location ID: " + id);
    }

    // Mettre à jour uniquement les champs nécessaires
    existingLocation.setDateLocation(location.getDateLocation());
    existingLocation.setDateDebut(location.getDateDebut());
    existingLocation.setDateRetour(location.getDateRetour());

    // Sauvegarder les modifications
    locationService.save(existingLocation);

    return "redirect:/locations"; // Redirige vers la liste des locations
}
@GetMapping("/delete/{id}")
public String deleteLocation(@PathVariable Long id) {
    Location location = locationService.findById(id);
    if (location == null) {
        throw new IllegalArgumentException("Invalid location ID: " + id);
    }
    locationService.delete(id); // Appelle le service pour supprimer la location
    return "redirect:/locations"; // Redirige vers la liste des locations
}

@PostMapping("/new")
public String saveLocation(@ModelAttribute("location") Location location, BindingResult result) {
    if (result.hasErrors()) {
        return "locations/form"; // Retourne le formulaire en cas d'erreurs
    }
    locationService.save(location); // Sauvegarde la location
    return "redirect:/locations"; // Redirige vers la liste des locations
}

}
