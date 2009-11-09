package com.kejikeji.common.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kejikeji.common.dao.ICommonDAO;


public class CommonDAOHibernate extends HibernateDaoSupport implements ICommonDAO {

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);

	}

	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	public List find(String queryString, Object... value) {
		return getHibernateTemplate().find(queryString,value);
	}

	public void save(Object obj) {
		getHibernateTemplate().save(obj);

	}

	public void update(Object obj) {
		getHibernateTemplate().update(obj);
		
	}

	public Object get(Class entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public Object load(Class entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}
	
    public void find(String queryString, PageBean pageObj, Object ... values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(queryString,pageObj,values));
    }

	public void refresh(Object obj) {
		getHibernateTemplate().refresh(obj);
		
	}

	public void deleteAll(String queryString,Object...value) {
		getHibernateTemplate().deleteAll(find(queryString,value));
		
	}

	public void bulkUpdate(String queryString, Object... value) {
		getHibernateTemplate().bulkUpdate(queryString, value);
		
	}

	public List find(String queryString, String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedParam(queryString,paramNames,values);
		
	}

	public void find(String queryString, PageBean pageObj, String[] paramNames, Object[] values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(queryString,pageObj,values,paramNames));
	}

	public void initialize(Object obj) {
		getHibernateTemplate().initialize(obj);
		
	}

	public void evict(Object obj) {
		getHibernateTemplate().evict(obj);
		
	}

	public void flush() {
		getHibernateTemplate().flush();
		
	}

	public void evictList(List list) {
		for (Object object : list) {
			evict(object);
		}
		
	}

	public void clear() {
		getHibernateTemplate().clear();
		
	}

	public Object get(Class entityClass, Serializable id, LockMode lockMode) {
		return getHibernateTemplate().get(entityClass, id, lockMode);
	}

	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
		
	}

}
