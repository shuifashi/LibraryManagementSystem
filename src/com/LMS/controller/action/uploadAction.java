package com.LMS.controller.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import com.LMS.model.forms.BookForm;
import com.LMS.model.service.BookManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class uploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadFileName;	
	private String uploadContentType;
	private long maximumSize;
	private String allowedTypes;
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
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		System.out.println(uploadFileName);
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public long getMaximumSize() {
		return maximumSize;
	}
	public void setMaximumSize(long maximumSize) {
		this.maximumSize = maximumSize;
	}
	public String getAllowedTypes() {
		return allowedTypes;
	}
	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}
	@Override
	public String execute() throws Exception,HibernateException, InterruptedException {
		File uploadFile = new File(ServletActionContext.getServletContext().getRealPath("WebPage\\upload"));
		if(!uploadFile.exists()) {
			uploadFile.mkdir();
		}
		ActionContext.getContext().put("name",uploadFile.getPath());
		//验证文件大小及格式
		if (upload == null || book.getBookName().equals("")|| book.getAuthor().equals("")||book.getBookAbstract().equals("")
				||book.getISBN().equals("")||book.getLocation().equals("")||book.getPrice().equals("")) {
			ActionContext.getContext().put("status","必须输入信息!");
			status = "0";
			return "error";
		}
		if (maximumSize < upload.length()) {
			ActionContext.getContext().put("status","文件过大!");
			status = "0";
			return "error";
		}	
		boolean flag =false;
		String[] allowedTypesStr = allowedTypes.split(",");
		for (int i = 0; i < allowedTypesStr.length; i++) {
			if (uploadContentType.equals(allowedTypesStr[i])) {
				flag = true;
			}
		}
		if (flag == false) {
			//Map request = (Map)ActionContext.getContext().get("request");
			//request.put("errorMassage", "文件类型不合法！");
			ActionContext.getContext().put("status","文件类型不合法!");
			status = "0";
			return "error";
		}
		String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		System.out.println(uploadFileName.substring(uploadFileName.lastIndexOf(".")));
		System.out.println(uploadContentType);
		FileUtils.copyFile(upload, new File(uploadFile+"\\"+book.getBookName()+fileType));
		//删除临时文件
		upload.delete();
		try {
			book.setPicture(book.getBookName()+fileType);
			BookManager.addBook(book);
			ActionContext.getContext().put("status","成功!");
			status = "1";
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("status","Information is not completed!");
			status = "0";
			return ERROR;
		}
	}	
	
}