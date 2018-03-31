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

import com.gdc.DAOInterface.IEmployerDAO;
import com.gdc.modele.Employer;
import com.gdc.modele.Entreprise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Employer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gdc.modele.Employer
 * @author MyEclipse Persistence Tools
 */
@Repository
public class EmployerDAO implements IEmployerDAO,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(EmployerDAO.class);
	// property constants
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";

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
	public void save(Employer transientInstance) {
		log.debug("saving Employer instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Employer persistentInstance) {
		log.debug("deleting Employer instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@Override
	public void update(Employer persistentInstance) {
		log.debug("update Employer instance");
		try {
			getCurrentSession().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public Employer findById(java.lang.Integer id) {
		log.debug("getting Employer instance with id: " + id);
		try {
			Employer instance = (Employer) getCurrentSession().get("com.gdc.modele.Employer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Employer> findByExample(Employer instance) {
		log.debug("finding Employer instance by example");
		try {
			List<Employer> results = (List<Employer>) getCurrentSession().createCriteria("com.gdc.modele.Employer")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Employer> findByQuery(String query) {
		log.debug("finding all Employer instances");
		try {
			String queryString = "from Employer "+query;
			List<Employer> list=getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Employer> findByProperty(String propertyName, Object value) {
		log.debug("finding Employer instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Employer as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<Employer> list=queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Employer> findByLogin(Object login) {
		return findByProperty(LOGIN, login);
	}

	public List<Employer> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	@Override
	public List<Employer> findAll() {
		log.debug("finding all Employer instances");
		try {
			String queryString = "from Employer";
			List<Employer> list = getCurrentSession().createQuery(queryString).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Employer merge(Employer detachedInstance) {
		log.debug("merging Employer instance");
		try {
			Employer result = (Employer) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Employer instance) {
		log.debug("attaching dirty Employer instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Employer instance) {
		log.debug("attaching clean Employer instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EmployerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EmployerDAO) ctx.getBean("EmployerDAO");
	}
}