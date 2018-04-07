package com.gdc.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gdc.modele.Employer;
import com.gdc.serviceInterface.IEmployerService;

@Component("authentificationActionBeans")
@Scope("session")
public class AuthentificationActionBeans implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	IEmployerService employerService;
	
	/**
	 * 
	 */
	
	
	
	@PostConstruct
	public void init(){
		employer=new Employer();
	}
	
	public AuthentificationActionBeans() {
	}

	private Employer employer;

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	
	public String authentifier(){
		String page="";
		if(employer!=null && employer.getLogin()!=null && employer.getLogin()!="" && employer.getLogin().trim()!="" 
				&& employer.getPassword()!=null && employer.getPassword()!="" && employer.getPassword().trim()!=""){
			if(employerService.findAll().size()==0){
				employerService.save(employer);
				page="entreprise";
			}else{
				if(employerService.findByQuery(" where login='"+employer.getLogin()+"' and password='"+employer.getPassword()+"'").size()>0){
					page="acceuil?faces-redirect=true";
				}else{
					page="";
				}
			}
		}
		return page;
	}
	
	

}
