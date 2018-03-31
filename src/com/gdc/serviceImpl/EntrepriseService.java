package com.gdc.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdc.DAOInterface.IEntrepriseDAO;
import com.gdc.modele.Entreprise;
import com.gdc.serviceInterface.IEntrepriseService;

@Service("entrepriseService")
@Transactional
public class EntrepriseService implements IEntrepriseService, Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	IEntrepriseDAO entrepriseDAO;
	
	/**
	 * 
	 */

	@Override
	public void delete(Entreprise persistentInstance) {	
		entrepriseDAO.delete(persistentInstance);
	}

	@Override
	public void save(Entreprise transientInstance) {
		entrepriseDAO.save(transientInstance);
	}

	@Override
	public void update(Entreprise persistentInstance) {
		entrepriseDAO.update(persistentInstance);
	}

	@Override
	@Transactional
	public Entreprise findById(Integer id) {
		return entrepriseDAO.findById(id);
	}

	@Override
	@Transactional
	public List<Entreprise> findByExample(Entreprise instance) {
		return entrepriseDAO.findByExample(instance);
	}

	@Override
	@Transactional
	public Entreprise merge(Entreprise detachedInstance) {
		return entrepriseDAO.merge(detachedInstance);
	}

	@Override
	@Transactional
	public List<Entreprise> findByQuery(String query) {
		return entrepriseDAO.findByQuery(query);
	}

	@Override
	@Transactional
	public List<Entreprise> findByProperty(String propertyName, Object value) {
		return entrepriseDAO.findByProperty(propertyName, value);
	}

	@Override
	@Transactional
	public List<Entreprise> findAll() {
		return entrepriseDAO.findAll();
	}

}
