package com.LMS.model.service;

import java.text.ParseException;
import java.util.List;

import org.hibernate.HibernateException;

import com.LMS.model.forms.RecordForm;

public interface RecordManager {
	public String Reserve(RecordForm recordForm) throws HibernateException, InterruptedException, ParseException;
	public String Borrow(RecordForm recordForm) throws HibernateException, InterruptedException;
	public String delReserve(RecordForm recordFor) throws HibernateException, InterruptedException;
	public String delBorrow(RecordForm recordForm) throws HibernateException, InterruptedException;
	public List<Object> findRecordbyUserId(RecordForm recordForm) throws HibernateException, InterruptedException;
	public List<Object> findAllBorrowRecord() throws HibernateException, InterruptedException;
	public List<Object> findAllReserveRecord() throws HibernateException, InterruptedException;
	public List<Object> findallRecord() throws HibernateException, InterruptedException;
	
}
