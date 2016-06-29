package com.LMS.controller.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.forms.BookForm;
import com.LMS.model.service.BookManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class normalfindBookAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String searchContent;
	private String searchType;
	private BookManager BookManager;
	private List bookList=new ArrayList();
	private String status;
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getSearchContent() {
		return this.searchContent;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchType() {
		return this.searchType;
	}
	public List getBookList() {
		 return bookList;
	}
	
	public void setBookList(List bookList) {
		 this.bookList = bookList;
	}

	public void setBookManager(BookManager BookManager) {
		this.BookManager = BookManager;
	}

	public String execute() throws HibernateException, InterruptedException, ParseException {
		System.out.println(searchContent);
		System.out.println(searchType);
		BookForm book = new BookForm();
		if(searchType.equals("option1")) {
			book.setBookName(searchContent);
		} else if(searchType.equals("option2")){
			book.setAuthor(searchContent);
		} else if(searchType.equals("option3")) {
			book.setISBN(searchContent);
		}
		BookForm searchbook = new BookForm();
		BeanUtils.copyProperties(book,searchbook);
		List<Object> resultbook = BookManager.normalfindBook(book);
		if(resultbook != null && resultbook.size() != 0) {
			bookList = resultbook;
			ActionContext.getContext().getSession().put("book1",resultbook);
			ActionContext.getContext().getSession().put("searchType","normal");
			ActionContext.getContext().getSession().put("searchBook", searchbook);
			BeanUtils.copyProperties(resultbook.get(0), book);
			System.out.println(book.getBookId());
			return SUCCESS;
		} else {
			return SUCCESS;
		}

	}

}
