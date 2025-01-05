package com.parkauto.rest.service;

import com.parkauto.rest.entity.Location;
import com.parkauto.rest.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private ILocationRepository locationRepository;

    /**
     * Récupère toutes les locations.
     *
     * @return une liste de toutes les locations
     */
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    /**
     * Récupère une location par son ID.
     *
     * @param id l'ID de la location
     * @return l'objet Location correspondant, ou null s'il n'existe pas
     */
    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    /**
     * Enregistre ou met à jour une location.
     *
     * @param location l'objet Location à enregistrer ou mettre à jour
     * @return la location enregistrée
     */
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    /**
     * Supprime une location.
     *
     * @param id l'ID de la location à supprimer
     */
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    /**
     * Met à jour une location existante.
     *
     * @param id       l'ID de la location à mettre à jour
     * @param location l'objet Location contenant les nouvelles données
     * @return la location mise à jour
     */
    public Location updateLocation(Long id, Location location) {
        Location existingLocation = getLocationById(id);
        if (existingLocation != null) {
            existingLocation.setDateLocation(location.getDateLocation());
            existingLocation.setDateDebut(location.getDateDebut());
            existingLocation.setDateRetour(location.getDateRetour());
            existingLocation.setClientList(location.getClientList());
            return locationRepository.save(existingLocation);
        }
        return null;
    }

    public void save(Location location) {
        // TODO Auto-generated method stub
         locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(Long id) {
        // TODO Auto-generated method stub
        return locationRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        locationRepository.deleteById(id); // Supprime l'entité par ID
    }
}

