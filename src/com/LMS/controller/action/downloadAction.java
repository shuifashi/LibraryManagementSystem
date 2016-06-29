package com.LMS.controller.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class downloadAction extends ActionSupport {
	
	private InputStream fileInput;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInput() {
		
		return ServletActionContext.getServletContext().getResourceAsStream("upload\\"+fileName);
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	
	public String execute() throws Exception{
		fileInput=ServletActionContext.getServletContext().getResourceAsStream("upload\\"+fileName);
		return "success";
	}
}

