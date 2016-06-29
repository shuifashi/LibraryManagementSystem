package com.LMS.controller.action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import java.util.Date; 

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.Book;
import com.LMS.model.forms.BookForm;
import com.LMS.model.service.BookManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class superfindBookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private BookForm book;

	private BookManager BookManager;
	
	private List bookList=new ArrayList();
	
	private String status;
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	
	public List getBookList() {
		 return bookList;
	}
	
	public void setBookList(List bookList) {
		 this.bookList = bookList;
	}
	public BookForm getBook() {
		return book;
	}

	public void setBook(BookForm Book) {
		this.book = Book;
	}

	public void setBookManager(BookManager BookManager) {
		this.BookManager = BookManager;
	}
	
	public String execute() throws HibernateException, InterruptedException, ParseException {
		BookForm searchbook = new BookForm();
		BeanUtils.copyProperties(book,searchbook);
		List<Object> resultbook = BookManager.superfindBook(book);
		if(resultbook != null && resultbook.size() != 0) {
			bookList = resultbook;
			ActionContext.getContext().getSession().put("book1",resultbook);
			ActionContext.getContext().getSession().put("searchType","super");
			ActionContext.getContext().getSession().put("searchBook", searchbook);
			BeanUtils.copyProperties(resultbook.get(0), book);
			ActionContext.getContext().getSession().put("book1",resultbook);
			System.out.println(book.getBookId());
			return SUCCESS;
		} else {
			return SUCCESS;
		}

	}
}
