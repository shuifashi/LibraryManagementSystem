package com.LMS.controller.action;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.*;
import com.LMS.model.forms.*;
import com.LMS.model.service.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class reserveBookAction extends ActionSupport {
	private RecordForm record;

	private RecordManager RecordManager;
	
	private BookManager BookManager;
	
	private String status;
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	
	public RecordForm getRecord() {
		return record;
	}
	
	public void setRecord(RecordForm record) {
		this.record = record;
	}
	
	public void setRecordManager(RecordManager RecordManager) {
		this.RecordManager = RecordManager;
	}
	public void setBookManager(BookManager BookManager) {
		this.BookManager = BookManager;
	}
	
	public String execute() throws HibernateException, InterruptedException, ParseException {
		if(record.getBookId() == null || record.getBookId().equals("")) {
			status = "«Î ‰»ÎbookId!";
			ActionContext.getContext().put("status",this.status);
			return ERROR;
		} else if(record.getUserId() == null || record.getUserId().equals("")){
			status = "«Î ‰»ÎUserId!";
			ActionContext.getContext().put("status",this.status);
			return ERROR;
		}
		status = this.RecordManager.Reserve(record);
		if(status.equals("Success")) {
			status = "1";
			ActionContext.getContext().put("status","Success!");
			String searchType = (String) ActionContext.getContext().getSession().get("searchType");
			BookForm searchBook = (BookForm) ActionContext.getContext().getSession().get("searchBook");
			List<Object> resultbook;
			if(searchType != null ) {
				if(searchType.equals("super")) {
					resultbook = BookManager.superfindBook(searchBook);
				}else{
					resultbook = BookManager.normalfindBook(searchBook);
				}
				ActionContext.getContext().getSession().put("book1",resultbook);
			}
			record = null;
			return SUCCESS;
		} else {
			ActionContext.getContext().put("status",status);
			status = "0";
			record = null;
			return ERROR;
		}
	}
}
