package com.gdc.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gdc.modele.Compte;
import com.gdc.serviceInterface.ICompteService;
import com.mchange.io.FileUtils;

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
	
	public void fileUpload(FileUploadEvent event) throws IOException {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        System.out.println(event.getFile().getFileName()+" -------- "+event.getFile().getContentType()+"------------");
        InputStream input = event.getFile().getInputstream();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
        String path = servletContext.getRealPath("");
        //File f=new File(input.);
		   /*UploadedFile file = event.getFile();
	        try{
	        	System.out.println("fileName: " + file.getFileName());
	            FileOutputStream fos = new FileOutputStream(new File(file.getFileName()));
	            InputStream is = file.getInputstream();
	            int BUFFER_SIZE = 8192;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            int a;
	            while(true){
	                a = is.read(buffer);
	                if(a < 0) break;
	                fos.write(buffer, 0, a);
	                fos.flush();
	            }
	            fos.close();
	            is.close();
	        }catch(IOException e){
	        }*/
    }

}
