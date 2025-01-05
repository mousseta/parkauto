package com.parkauto.rest.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.Commande;
import com.parkauto.rest.repository.ICommandeRepository;

@Service
public class CommandeService {

    @Autowired
    private ICommandeRepository commandeRepository;

    public List<Commande> getCommandes() {
        return commandeRepository.findAll();
    }

    public void saveCommande(Commande commande) {
        commandeRepository.save(commande);
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public void deleteCommandeById(Long id) {
        commandeRepository.deleteById(id);
    }
}
