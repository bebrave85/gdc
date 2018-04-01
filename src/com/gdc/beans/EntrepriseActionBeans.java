package com.gdc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gdc.modele.Entreprise;
import com.gdc.serviceInterface.IEntrepriseService;

@Component("entrepriseActionBeans")
@Scope("session")
public class EntrepriseActionBeans implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	IEntrepriseService entrepriseService;
	
	/**
	 * 
	 */
	
	private List<Entreprise> listEntreprise=new ArrayList<Entreprise>();
	private Entreprise entreprise;
	
	
	@PostConstruct
	public void init(){
		listEntreprise=entrepriseService.findAll();
		
	}


	public Entreprise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}


	public List<Entreprise> getListEntreprise() {
		return listEntreprise;
	}


	public void setListEntreprise(List<Entreprise> listEntreprise) {
		this.listEntreprise = listEntreprise;
	}
	
	public void viderAjouterEntreprise(){
		Entreprise e=new Entreprise();
		listEntreprise.add(e);
	}
	
	
	
	

}
