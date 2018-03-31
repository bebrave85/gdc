package com.gdc.beans;

import java.io.Serializable;

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
	
	private Entreprise entreprise;
	
	
	@PostConstruct
	public void init(){
		
	}


	public Entreprise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	

}
