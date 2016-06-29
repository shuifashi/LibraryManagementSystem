package com.LMS.model.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.LMS.model.beans.Record;
import com.LMS.model.beans.User;
import com.LMS.model.dao.BaseDao;

public class RecordDaoImpl extends HibernateDaoSupport implements BaseDao{

	@Override
	public void saveObject(Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(obj);
	}

	@Override
	public Object getObject(String queryString) throws HibernateException {
		// TODO Auto-generated method stub
		List<Record> ls = getHibernateTemplate().find(queryString);
		if(ls.size() != 0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public Object getObject(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		List<Record> ls = getHibernateTemplate().findByExample(object);
		if(ls.size() != 0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public List<Object> getObjectList(String queryString) throws HibernateException {
		// TODO Auto-generated method stub
		List<Object> ls = getHibernateTemplate().find(queryString);
		return ls;
	}
	public void update(Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(obj);
	}
	public void delete(Object obj) throws HibernateException {
		getHibernateTemplate().delete(obj);
	}

}
