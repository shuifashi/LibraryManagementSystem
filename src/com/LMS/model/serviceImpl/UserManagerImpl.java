package com.LMS.model.serviceImpl;
import mail.mailSender;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.User;
import com.LMS.model.beans.registerMail;
import com.LMS.model.dao.BaseDao;
import com.LMS.model.daoImpl.UserDaoImpl;
import com.LMS.model.forms.UserForm;
import com.LMS.model.service.UserManager;
import com.opensymphony.xwork2.ActionContext;

public class UserManagerImpl implements UserManager {

	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	@Override
	public void regUser(UserForm userForm) throws HibernateException, InterruptedException {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		registerMail email = new registerMail(user.getUserId(),user.getUsername(),user.getEmail());
		dao.saveObject(user);
		dao.saveObject(email);
		//ActionContext.getContext().put("status","Success");
		//String name = dao.getObject(user.getUsername());
		//System.out.println(name);
		//ActionContext.getContext().put("name",name);
		//ActionContext.getContext().getSession().put("name", name);
	}
	@Override
	public boolean loginUser(UserForm userForm) throws HibernateException, InterruptedException {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		String queryString= "from User as u where u.userId='"+user.getUserId()+"'";
		User result = (User) dao.getObject(queryString);
		//User result2 = (User)dao.getObject(user);
		//System.out.println(result2.getUsername());
		if(result != null && result.getUserId().equals(user.getUserId())&&result.getPassword().equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public User findUser(UserForm userForm) throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		String queryString= "from User as u where u.userId="+user.getUserId();
		User result = (User) dao.getObject(queryString);
		return result;
	}

}
