package com.gdc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gdc.modele.Compte;
import com.gdc.serviceInterface.ICompteService;

@Component("compteActionBeans")
@Scope("session")
public class CompteActionBeans implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	ICompteService compteService;
	
	/**
	 * 
	 */
	
	private List<Compte> listCompte=new ArrayList<Compte>();
	private Compte compte;
	
	
	@PostConstruct
	public void init(){
		listCompte=new ArrayList<Compte>();
		listCompte=compteService.findAll();		
	}
	

	public List<Compte> getListCompte() {
		return listCompte;
	}

	public void setListCompte(List<Compte> listCompte) {
		this.listCompte = listCompte;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public void viderAjouterCompte(){
		Compte e=new Compte();
		listCompte.add(e);
	}
	
	public void getAllListCompte(){
		listCompte=new ArrayList<Compte>();
		listCompte=compteService.findAll();		
	}
	
	public String viderCompte(){
		compte=new Compte();
		getAllListCompte();
		return "compte?faces-redirect=true";
	}
	
	public void ajouterCompte(RowEditEvent event){
		compte=new Compte();
		compte=(Compte) event.getObject();		
		if(compte!=null && compte.getNomCompte()!=null && compte.getNumCompte()!=null){
			if(compte.getId()!=null){
				compteService.update(compte);
				getAllListCompte();
			}else{
				compteService.save(compte);
				getAllListCompte();
			}			
		}
	}
	
	public void supprimerCompte(Long id){
		compte=new Compte();
		compte=compteService.findById(id);
		if(compte!=null && compte.getId()!=null){
			try {
				compteService.delete(compte);
				getAllListCompte();
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "");
		        FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}
	
	public void fileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
