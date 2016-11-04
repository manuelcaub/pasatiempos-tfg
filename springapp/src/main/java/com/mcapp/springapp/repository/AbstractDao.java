package com.mcapp.springapp.repository;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractDao<T> extends HibernateDaoSupport {
	
	private final Class<T> t;
	
	public AbstractDao(Class<T> t){
		this.t = t;
	}
	
	@Resource
	private void setSessionFactoryDao(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
 
	@Transactional
	public void saveOrUpdate(Object entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
 
	@Transactional
	public void saveOrUpdate(Object[] entities) {
		for (int i = 0; i < entities.length; i++) {
			this.saveOrUpdate(entities[i]);
		}
	}
 
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.getHibernateTemplate().loadAll(t);
	}
 
	@Transactional(readOnly = true)
	public T findByPk(Serializable id) {
		return this.getHibernateTemplate().load(t, id);
	}
 
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> find(String hql) {	
		return (List<T>)this.getHibernateTemplate().find(hql);
	} 
	
	public Session hibernate() {
		return this.getSessionFactory().getCurrentSession();
	}
}