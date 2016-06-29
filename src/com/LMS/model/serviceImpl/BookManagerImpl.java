package com.LMS.model.serviceImpl;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.Book;
import com.LMS.model.beans.User;
import com.LMS.model.beans.registerMail;
import com.LMS.model.dao.BaseDao;
import com.LMS.model.daoImpl.BookDaoImpl;
import com.LMS.model.forms.BookForm;
import com.LMS.model.service.BookManager;
import com.opensymphony.xwork2.ActionContext;
public class BookManagerImpl implements BookManager{
	private BaseDao dao;

	public void setDao(BookDaoImpl dao) {
		this.dao = dao;
	}
	@Override
	public void addBook(BookForm BookForm) throws HibernateException, InterruptedException {
		Book book = new Book();
		BeanUtils.copyProperties(BookForm, book);
		dao.saveObject(book);
	}
	public Object findBook(BookForm bookForm) throws HibernateException, InterruptedException {
		Book book = new Book();
		BeanUtils.copyProperties(bookForm, book);
		String queryString= "from Book as b where b.bookId = '"+book.getBookId()+"'";
		Object result= this.dao.getObject(queryString);
		return result;
	}
	@Override
	public List<Object> normalfindBook(BookForm bookForm) throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		Book book = new Book();
		String queryString;
		BeanUtils.copyProperties(bookForm, book);
		System.out.println(book.getBookName());
		if(book.getBookName() != null && !book.getBookName().equals("")) {
			queryString= "from Book as b where b.bookName like '%"+book.getBookName()+"%'";
		} else if(book.getAuthor() != null && !book.getAuthor().equals("")){
			queryString= "from Book as b where b.Author like '%"+book.getAuthor()+"%'";
		} else if(book.getISBN() != null && !book.getISBN().equals("")){
			queryString= "from Book as b where b.ISBN = '"+book.getISBN()+"'";
		} else {
			return null;
		}
		List<Object> result = dao.getObjectList(new String(queryString));
		//User result2 = (User)dao.getObject(user);
		//System.out.println(result2.getUsername());
		return result;
	}
	@Override
	public List<Object> superfindBook(BookForm bookForm) throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		Book book = new Book();
		BeanUtils.copyProperties(bookForm, book);
		StringBuffer queryString= new StringBuffer("from Book as b where ");
		int flag = 0;
		if(book.getBookName() != null && !book.getBookName().equals("")) {
			if(flag == 1)
				queryString.append("and ");
			queryString.append("b.bookName like '%"+book.getBookName()+"%' ");
			flag = 1;
		} 
		if(book.getAuthor() != null && !book.getAuthor().equals("")) {
			if(flag == 1)
				queryString.append("and ");
			queryString.append("b.Author like '%"+book.getAuthor()+"%' ");
			flag = 1;
		}
		if(book.getPublisher() != null && !book.getAuthor().equals("")) {
			if(flag == 1)
				queryString.append("and ");
			queryString.append("b.Publisher like '%"+book.getPublisher()+"%' ");
			flag = 1;
		}
		if(flag == 0)
			return null;
		System.out.println(queryString);
		List<Object> result = dao.getObjectList(new String(queryString));
		//User result2 = (User)dao.getObject(user);
		//System.out.println(result2.getUsername());
		return result;
	}

}
