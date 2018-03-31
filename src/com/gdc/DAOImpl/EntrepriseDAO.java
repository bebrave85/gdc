package com.gdc.DAOImpl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gdc.DAOInterface.IEntrepriseDAO;
import com.gdc.modele.Employer;
import com.gdc.modele.Entreprise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Entreprise entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gdc.modele.Entreprise
 * @author MyEclipse Persistence Tools
 */
@Repository
public class EntrepriseDAO implements IEntrepriseDAO , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EntrepriseDAO.class);
	// property constants
	public static final String NOM = "nom";
	public static final String NUM_IF = "numIf";
	public static final String ICE = "ice";
	public static final String RC = "rc";
	public static final String CNSS = "cnss";

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	@Override
	public void save(Entreprise transientInstance) {
		log.debug("saving Entreprise instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Entreprise persistentInstance) {
		log.debug("deleting Entreprise instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@Override
	public void update(Entreprise persistentInstance) {
		log.debug("updating Entreprise instance");
		try {
			getCurrentSession().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public Entreprise findById(java.lang.Integer id) {
		log.debug("getting Entreprise instance with id: " + id);
		try {
			Entreprise instance = (Entreprise) getCurrentSession().get("com.gdc.modele.Entreprise", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Entreprise> findByExample(Entreprise instance) {
		log.debug("finding Entreprise instance by example");
		try {
			List<Entreprise> results = (List<Entreprise>) getCurrentSession()
					.createCriteria("com.gdc.modele.Entreprise").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Entreprise> findByProperty(String propertyName, Object value) {
		log.debug("finding Entreprise instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Entreprise as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<Entreprise> list=queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Entreprise> findByNom(Object nom) {
		return findByProperty(NOM, nom);
	}

	public List<Entreprise> findByNumIf(Object numIf) {
		return findByProperty(NUM_IF, numIf);
	}

	public List<Entreprise> findByIce(Object ice) {
		return findByProperty(ICE, ice);
	}

	public List<Entreprise> findByRc(Object rc) {
		return findByProperty(RC, rc);
	}

	public List<Entreprise> findByCnss(Object cnss) {
		return findByProperty(CNSS, cnss);
	}

	@Override
	public List<Entreprise> findAll() {
		log.debug("finding all Entreprise instances");
		try {
			String queryString = "from Entreprise";
			List<Entreprise> list=getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Entreprise> findByQuery(String query) {
		log.debug("finding all Entreprise instances");
		try {
			String queryString = "from Entreprise "+query;
			List<Entreprise> list=getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Entreprise merge(Entreprise detachedInstance) {
		log.debug("merging Entreprise instance");
		try {
			Entreprise result = (Entreprise) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Entreprise instance) {
		log.debug("attaching dirty Entreprise instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Entreprise instance) {
		log.debug("attaching clean Entreprise instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntrepriseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EntrepriseDAO) ctx.getBean("EntrepriseDAO");
	}
}