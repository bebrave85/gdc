package com.gdc.DAOInterface;

import java.util.List;

import com.gdc.modele.Employer;

public interface IEmployerDAO {
	
	void delete(Employer persistentInstance);
	void save(Employer transientInstance);
	void update(Employer persistentInstance);
	Employer findById(Integer id);
	List<Employer> findByExample(Employer instance);
	Employer merge(Employer detachedInstance);
	List<Employer> findByQuery(String query);
	List<Employer> findByProperty(String propertyName, Object value);
	List<Employer> findAll();

}
