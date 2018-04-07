package com.gdc.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compte", catalog = "gdc")
public class Compte implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer numCompte ;
	private String nomCompte;
	private String classCompte;
	private String rubrique;

	
	
	// Constructors

	/** default constructor */
	public Compte() {
	}

	
	/** full constructor */
	public Compte(Integer numCompte,String nomCompte,String classCompte,String rubrique) {
		this.numCompte=numCompte;
		this.nomCompte=nomCompte;
		this.classCompte=classCompte;
		this.rubrique=rubrique;
	}
	

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", unique = true, nullable = false)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "numCompte", unique = true ,nullable = false, length = 75)
	public Integer getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(Integer numCompte) {
		this.numCompte = numCompte;
	}

	@Column(name = "nomCompte", length = 75)
	public String getNomCompte() {
		return nomCompte;
	}

	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}
	
	@Column(name = "classCompte" , length = 75)
	public String getClassCompte() {
		return classCompte;
	}

	public void setClassCompte(String classCompte) {
		this.classCompte = classCompte;
	}
	
	@Column(name = "rubrique" , length = 75)
	public String getRubrique() {
		return rubrique;
	}

	public void setRubrique(String rubrique) {
		this.rubrique = rubrique;
	}

	
	


}
