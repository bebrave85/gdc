package com.gdc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
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
		listEntreprise=new ArrayList<Entreprise>();
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
	
	public void getAllListEntreprise(){
		listEntreprise=new ArrayList<Entreprise>();
		listEntreprise=entrepriseService.findAll();	
	}
	
	public String viderEntreprise(){
		entreprise=new Entreprise();
		getAllListEntreprise();
		return "entreprise?faces-redirect=true";
	}
	
	public void ajouterEntreprise(RowEditEvent event){
		entreprise=new Entreprise();
		entreprise=(Entreprise) event.getObject();		
		if(entreprise!=null && entreprise.getCnss()!=null && entreprise.getIce()!=null
				&& entreprise.getNom()!=null && entreprise.getNumIf()!=null && entreprise.getRc()!=null){
			if(entreprise.getId()!=null){
				entrepriseService.update(entreprise);
				getAllListEntreprise();
			}else{
				entrepriseService.save(entreprise);
				getAllListEntreprise();
			}			
		}
	}
	
	public void supprimerEntreprise(Integer id){
		entreprise=new Entreprise();
		entreprise=entrepriseService.findById(id);
		if(entreprise!=null && entreprise.getId()!=null){
			try {
				entrepriseService.delete(entreprise);
				getAllListEntreprise();
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "");
		        FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}
	
	
	
	

}
