package com.gdc.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdc.DAOInterface.IEmployerDAO;
import com.gdc.modele.Employer;
import com.gdc.serviceInterface.IEmployerService;


@Service("employerService")
@Transactional
public class EmployerService implements IEmployerService,Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	@Autowired
	IEmployerDAO employerDAO;
	
	/**
	 * 
	 */
	

	@Override
	@Transactional
	public void delete(Employer persistentInstance) {
		employerDAO.delete(persistentInstance);
	}

	@Override
	@Transactional
	public void save(Employer transientInstance) {	
		employerDAO.save(transientInstance);
	}

	@Override
	@Transactional
	public void update(Employer persistentInstance) {	
		employerDAO.update(persistentInstance);
	}

	@Override
	public Employer findById(Integer id) {
		return employerDAO.findById(id);
	}

	@Override
	public List<Employer> findByExample(Employer instance) {
		return employerDAO.findByExample(instance);
	}

	@Override
	public Employer merge(Employer detachedInstance) {
		return employerDAO.merge(detachedInstance);
	}

	@Override
	@Transactional
	public List<Employer> findByQuery(String query) {
		return employerDAO.findByQuery(query);
	}

	@Override
	@Transactional
	public List<Employer> findByProperty(String propertyName, Object value) {
		return employerDAO.findByProperty(propertyName, value);
	}

	@Override
	@Transactional
	public List<Employer> findAll() {
		return employerDAO.findAll();
	}

}
