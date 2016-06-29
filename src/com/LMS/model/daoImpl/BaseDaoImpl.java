package com.LMS.model.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.LMS.model.beans.User;
import com.LMS.model.dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{
	@Override
	public void saveObject(Object obj) throws HibernateException {
		getHibernateTemplate().save(obj);
	}

	@Override
	public Object getObject(String queryString) throws HibernateException {
		List<User> ls = getHibernateTemplate().find(queryString);
		if(ls.size() != 0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public List<Object> getObjectList(String queryString) throws HibernateException {
		List<Object> ls = getHibernateTemplate().find(queryString);
		return ls;
	}

	@Override
	public Object getObject(Object object) throws HibernateException {
		List<User> ls = getHibernateTemplate().findByExample(object);
		if(ls.size() != 0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public void update(Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(obj);
	}
	@Override
	public void delete(Object obj) throws HibernateException {
		getHibernateTemplate().delete(obj);
	}
}
