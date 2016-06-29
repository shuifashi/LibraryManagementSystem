package com.LMS.model.service;

import org.hibernate.HibernateException;

import com.LMS.model.beans.User;
import com.LMS.model.forms.UserForm;

public interface UserManager {

	public void regUser(UserForm userForm) throws HibernateException, InterruptedException;
	public boolean loginUser(UserForm userForm) throws HibernateException, InterruptedException;
	public User findUser(UserForm userForm) throws HibernateException, InterruptedException;
	
}
