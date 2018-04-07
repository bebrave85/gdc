package com.gdc.DAOImpl;

import static org.hibernate.criterion.Example.create;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdc.DAOInterface.ICompteDAO;
import com.gdc.modele.Compte;

/**
 * A data access object (DAO) providing persistence and search support for
 * Compte entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gdc.modele.Compte
 * @author MyEclipse Persistence Tools
 */
@Repository
public class CompteDAO implements ICompteDAO,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(CompteDAO.class);
	// property constants

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
	public void save(Compte transientInstance) {
		log.debug("saving Compte instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Compte persistentInstance) {
		log.debug("deleting Compte instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@Override
	public void update(Compte persistentInstance) {
		log.debug("update Compte instance");
		try {
			getCurrentSession().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public Compte findById(Long id) {
		log.debug("getting Compte instance with id: " + id);
		try {
			Compte instance = (Compte) getCurrentSession().get("com.gdc.modele.Compte", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Compte> findByExample(Compte instance) {
		log.debug("finding Compte instance by example");
		try {
			List<Compte> results = (List<Compte>) getCurrentSession().createCriteria("com.gdc.modele.Compte")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Compte> findByQuery(String query) {
		log.debug("finding all Compte instances");
		try {
			String queryString = "from Compte "+query;
			List<Compte> list=getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Compte> findByProperty(String propertyName, Object value) {
		log.debug("finding Compte instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Compte as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<Compte> list=queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	@Override
	public List<Compte> findAll() {
		log.debug("finding all Compte instances");
		try {
			String queryString = "from Compte";
			List<Compte> list = getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Compte merge(Compte detachedInstance) {
		log.debug("merging Compte instance");
		try {
			Compte result = (Compte) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Compte instance) {
		log.debug("attaching dirty Compte instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Compte instance) {
		log.debug("attaching clean Compte instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CompteDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CompteDAO) ctx.getBean("CompteDAO");
	}
}