package com.LMS.model.dao;

import java.util.List;

import org.hibernate.HibernateException;

public interface BaseDao {
	public void saveObject(Object obj) throws HibernateException;
	public Object getObject(String queryString) throws HibernateException;
	public Object getObject(Object object)throws HibernateException;
	public List<Object> getObjectList(String queryString) throws HibernateException;
	public void update(Object obj) throws HibernateException;
	public void delete(Object obj) throws HibernateException;
}
