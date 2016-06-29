package com.LMS.controller.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.Book;
import com.LMS.model.forms.BookForm;
import com.LMS.model.service.BookManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class addBookAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private BookForm book;

	private BookManager BookManager;
	
	private String status;
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	
	public BookForm getBook() {
		return book;
	}

	public void setBook(BookForm Book) {
		this.book = Book;
		this.book.setFlag(1);
	}

	public void setBookManager(BookManager BookManager) {
		this.BookManager = BookManager;
	}

	public String execute() throws HibernateException, InterruptedException {
		try {
			BookManager.addBook(book);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("status","Information is not completed!");
			return ERROR;
		}
	}
	
}
