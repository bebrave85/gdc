package com.gdc.serviceInterface;

import java.util.List;

import com.gdc.modele.Compte;

public interface ICompteService {

	void delete(Compte persistentInstance);
	void save(Compte transientInstance);
	void update(Compte persistentInstance);
	Compte findById(Long id);
	List<Compte> findByExample(Compte instance);
	Compte merge(Compte detachedInstance);
	List<Compte> findByQuery(String query);
	List<Compte> findByProperty(String propertyName, Object value);
	List<Compte> findAll();
	

}
