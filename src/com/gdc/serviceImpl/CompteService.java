package com.gdc.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdc.DAOInterface.ICompteDAO;
import com.gdc.modele.Compte;
import com.gdc.serviceInterface.ICompteService;


@Service("compteService")
@Transactional
public class CompteService implements ICompteService,Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	ICompteDAO compteDAO;
	
	/**
	 * 
	 */
	

	@Override
	@Transactional
	public void delete(Compte persistentInstance) {
		compteDAO.delete(persistentInstance);
	}

	@Override
	@Transactional
	public void save(Compte transientInstance) {	
		compteDAO.save(transientInstance);
	}

	@Override
	@Transactional
	public void update(Compte persistentInstance) {	
		compteDAO.update(persistentInstance);
	}

	@Override
	public Compte findById(Long id) {
		return compteDAO.findById(id);
	}

	@Override
	public List<Compte> findByExample(Compte instance) {
		return compteDAO.findByExample(instance);
	}

	@Override
	public Compte merge(Compte detachedInstance) {
		return compteDAO.merge(detachedInstance);
	}

	@Override
	@Transactional
	public List<Compte> findByQuery(String query) {
		return compteDAO.findByQuery(query);
	}

	@Override
	@Transactional
	public List<Compte> findByProperty(String propertyName, Object value) {
		return compteDAO.findByProperty(propertyName, value);
	}

	@Override
	@Transactional
	public List<Compte> findAll() {
		return compteDAO.findAll();
	}

}
