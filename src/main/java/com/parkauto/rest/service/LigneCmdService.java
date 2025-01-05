package com.parkauto.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkauto.rest.entity.LigneCmd;
import com.parkauto.rest.repository.ILigneCmdRepository;

@Service
public class LigneCmdService {
	@Autowired
	ILigneCmdRepository ligneCmdRepository;
	
	//Liste des véhicules
	public List<LigneCmd> getLigneCmds(){
		return ligneCmdRepository.findAll();
		
	}
	
	//Save
	public LigneCmd saveLigneCmd(LigneCmd ligneCmd) {
		return ligneCmdRepository.save(ligneCmd);
		
	}
	
	//Get a ligneCmd
	public LigneCmd getLigneCmdById(Long idligneC) {
		return ligneCmdRepository.findById(idligneC).get();
		
	}
	
	//Delete a ligneCmd
	public void deleteLigneCmd(LigneCmd ligneCmd) {
		ligneCmdRepository.delete(ligneCmd);
	}
	
	//Mise  à jour d'une ligneCmd
	public LigneCmd updateLigneCmd(Long idLigneCmd, LigneCmd ligneCmd) {
		
		LigneCmd ligneC = ligneCmdRepository.findById(idLigneCmd).get();
		ligneC.setLibelle(ligneCmd.getLibelle());
		ligneC.setQte(ligneCmd.getQte());
		ligneC.setPrix(ligneCmd.getPrix());
		
		
		return ligneCmdRepository.save(ligneC);
	}
	
}
