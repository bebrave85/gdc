package com.gdc.DAOInterface;

import java.util.List;

import com.gdc.modele.Entreprise;

public interface IEntrepriseDAO {

	void delete(Entreprise persistentInstance);
	void save(Entreprise transientInstance);
	void update(Entreprise persistentInstance);
	Entreprise findById(Integer id);
	List<Entreprise> findByExample(Entreprise instance);
	Entreprise merge(Entreprise detachedInstance);
	List<Entreprise> findByQuery(String query);
	List<Entreprise> findByProperty(String propertyName, Object value);
	List<Entreprise> findAll();
	

}
