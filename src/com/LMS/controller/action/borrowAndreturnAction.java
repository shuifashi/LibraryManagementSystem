package com.LMS.controller.action;
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
public class borrowAndreturnAction extends ActionSupport {
	private RecordForm record;
	
	private String actionType;

	private RecordManager RecordManager;
	
	private String status;
	
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionType() {
		return this.actionType;
	}
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
	
	public String execute() throws HibernateException, InterruptedException {
		if(record.getBookId() == null || record.getBookId().equals("")) {
			status = "«Î ‰»ÎbookId!";
			ActionContext.getContext().put("status",this.status);
			status = "0";
			return ERROR;
		} else if(actionType.equals("borrow") &&(record.getUserId() == null || record.getUserId().equals(""))){
			status = "«Î ‰»ÎUserId!";
			ActionContext.getContext().put("status",this.status);
			status = "0";
			return ERROR;
		}
		if(actionType.equals("borrow"))
			status = this.RecordManager.Borrow(record);
		else
			status = this.RecordManager.delBorrow(record);
		if(status.equals("Success")) {
			ActionContext.getContext().put("status",status);
			status = "1";
			return SUCCESS;
		} else {
			ActionContext.getContext().put("status",this.status);
			status = "0";
			return ERROR;
		}
	}
}
