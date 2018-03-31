package com.gdc.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entreprise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "entreprise", catalog = "gdc")

public class Entreprise implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nom;
	private Integer numIf;
	private Integer ice;
	private Integer rc;
	private Integer cnss;

	// Constructors

	/** default constructor */
	public Entreprise() {
	}

	/** full constructor */
	public Entreprise(String nom, Integer numIf, Integer ice, Integer rc, Integer cnss) {
		this.nom = nom;
		this.numIf = numIf;
		this.ice = ice;
		this.rc = rc;
		this.cnss = cnss;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nom", nullable = false, length = 75)

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "Num_IF", nullable = false)

	public Integer getNumIf() {
		return this.numIf;
	}

	public void setNumIf(Integer numIf) {
		this.numIf = numIf;
	}

	@Column(name = "ICE", nullable = false)

	public Integer getIce() {
		return this.ice;
	}

	public void setIce(Integer ice) {
		this.ice = ice;
	}

	@Column(name = "RC", nullable = false)

	public Integer getRc() {
		return this.rc;
	}

	public void setRc(Integer rc) {
		this.rc = rc;
	}

	@Column(name = "Cnss", nullable = false)

	public Integer getCnss() {
		return this.cnss;
	}

	public void setCnss(Integer cnss) {
		this.cnss = cnss;
	}

}