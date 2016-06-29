package com.LMS.model.serviceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.LMS.model.beans.Book;
import com.LMS.model.beans.Record;
import com.LMS.model.beans.User;
import com.LMS.model.beans.registerMail;
import com.LMS.model.beans.reserveMail;
import com.LMS.model.dao.BaseDao;
import com.LMS.model.daoImpl.BookDaoImpl;
import com.LMS.model.daoImpl.RecordDaoImpl;
import com.LMS.model.daoImpl.UserDaoImpl;
import com.LMS.model.forms.RecordForm;
import com.LMS.model.service.BookManager;
import com.LMS.model.service.RecordManager;
import com.opensymphony.xwork2.ActionContext;
public class RecordManagerImpl implements RecordManager{
	private BookDaoImpl bookDao;
	private RecordDaoImpl recordDao;
	private UserDaoImpl userDao;
	public void setBookDao(BookDaoImpl bookDao) {
		this.bookDao = bookDao;
	}
	public void setRecordDao(RecordDaoImpl recordDao) {
		this.recordDao = recordDao;
	}
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	@Override
	public List<Object> findRecordbyUserId(RecordForm recordForm) throws HibernateException, InterruptedException {
		if(recordForm != null && !recordForm.getUserId().equals("")) {
			Record record = new Record();
			BeanUtils.copyProperties(recordForm, record);
			StringBuffer queryString = new StringBuffer("from Record as r where r.userId = '"+record.getUserId()+"' order by r.beginTime");
			List<Object> list = this.recordDao.getObjectList(new String(queryString));
			return list;
		} else {
			StringBuffer queryString = new StringBuffer("from Record as r order by r.beginTime");
			List<Object> list = this.recordDao.getObjectList(new String(queryString));
			return list;
		}
	}
	@Override
	public synchronized String Reserve(RecordForm recordForm) throws HibernateException, InterruptedException, ParseException {
		Record record = new Record();
		BeanUtils.copyProperties(recordForm, record);	
		String statement = new String("from Book as b where b.bookId = '"+record.getBookId()+"'");
		Book book = (Book)bookDao.getObject(statement);
		statement = new String("from User as u where u.userId = '"+record.getUserId()+"'");
		User user = (User)userDao.getObject(statement);
		statement = new String("from Record as r where r.bookId = '"+record.getBookId()+"' and r.type = 0");
		Record borrowhistory = (Record)recordDao.getObject(statement);
		if(user.getFlag() == 1 && book.getFlag() == 2 && borrowhistory != null && !borrowhistory.getUserId().equals(record.getUserId())) {
			SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date beginDay = new Date();
		    record.setType(1);
		    record.setFlag(1);
		    record.setBeginTime(sp.format(beginDay));
		    this.recordDao.saveObject(record);
		    book.setFlag(3);
			this.bookDao.update(book);
			Record reserveRecord = (Record)recordDao.getObject(record);
			reserveMail reservemail = new reserveMail(reserveRecord.getRecordId(),user.getEmail(),user.getUsername(),book.getBookName(),null,0);
			this.bookDao.saveObject(reservemail);
			return "Success";
		} else {
			System.out.println(user.getFlag());
			if(user.getFlag() == 0)
				return "用户权限被暂停!";
			else if(book.getFlag() == 1)
				return "书籍尚未借出，请直接前往图书馆借书!";
			else if(book.getFlag() == 3)
				return "书籍已经被预约!";
			else if(book.getFlag() == 4)
				return "书籍已经被预约!";
			else if(borrowhistory.getUserId().equals(record.getUserId()))
				return "不可以预约自己借走的书籍!";
			else
				return "fail!";
		}
	}
	@Override
	public synchronized String Borrow(RecordForm recordForm) throws HibernateException, InterruptedException {
		Record record = new Record();
		BeanUtils.copyProperties(recordForm, record);	
		if(this.bookDao != null && this.recordDao != null && this.userDao != null)
			System.out.println("yes!!");
		String statement = new String("from Book as b where b.bookId = '"+record.getBookId()+"'");
		Book book = (Book)bookDao.getObject(statement);
		if(book == null) {
			return "书籍不存在!";
		}
		statement = new String("from User as u where u.userId = '"+record.getUserId()+"'");
		User user = (User)userDao.getObject(statement);
		if(user == null) {
			return "用户不存在!";
		}
		if(book.getFlag() == 1 && user.getFlag() == 1) {
			/*String s="2016-06-02 23:12:21";   
		    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
		    java.util.Date du = sp.parse(s);
		    Calendar calendar=Calendar.getInstance();
		    calendar.setTime(du);	
		    calendar.add(Calendar.DAY_OF_MONTH,30);
		    java.util.Date endday = calendar.getTime();
		    System.out.println(du.toString());
		    System.out.println(sp.format(du));
		    System.out.println(sp.format(endday));*/
			SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date beginDay = new Date();
			Calendar calendar=Calendar.getInstance();
		    calendar.setTime(beginDay);	
		    calendar.add(Calendar.DAY_OF_MONTH,30);
		    Date endDay = calendar.getTime();
		    record.setType(0);
		    record.setFlag(1);
		    record.setBeginTime(sp.format(beginDay));
		    record.setEndTime(sp.format(endDay));
		    this.recordDao.saveObject(record);
		    book.setFlag(2);
			this.bookDao.update(book);
			System.out.println("OK");
			return "Success";
		} else {
			if(book.getFlag() == 2 || book.getFlag() == 3)
				return "书籍已经被借出!";
			else if(book.getFlag() == 4)
				return "书籍已经被预约!";
			else
				return "用户权限被暂停!";
		}
	}
	@Override
	public synchronized String delReserve(RecordForm recordForm) throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		Record re = new Record();
		BeanUtils.copyProperties(recordForm, re);	
		String statement = new String("from Record as r where r.userId = '"+ re.getUserId()+"' and r.bookId = '"+re.getBookId()+"'");
		Record record = (Record)this.recordDao.getObject(statement);
		System.out.println(record.getRecordId());
		statement = new String("from Book as b where b.bookId = '"+record.getBookId()+"'");
		Book book = (Book)bookDao.getObject(statement);
		if(record.getType() == 1) {
			statement = new String("from Record as r where r.recordId = '"+record.getRecordId()+"'");
			Record history = (Record)this.recordDao.getObject(statement);
			statement = new String("from Record as r where r.bookId = '"+record.getBookId()+"' and r.type = 0");
			Record borrow = (Record)this.recordDao.getObject(statement);
			if(borrow != null)
				book.setFlag(2);
			else
				book.setFlag(1);
			this.bookDao.update(book);
			this.recordDao.delete(history);
			return "Success";
		}
		return "Fail";
	}
	@Override
	public synchronized String delBorrow(RecordForm recordForm) throws HibernateException, InterruptedException {
		Record record = new Record();
		BeanUtils.copyProperties(recordForm, record);	
		String statement = new String("from Book as b where b.bookId = '"+record.getBookId()+"'");
		Book book = (Book)bookDao.getObject(statement);
		if(book != null)
			System.out.println(book.getBookId());
		statement = new String("from Record as r where r.bookId = '"+record.getBookId()+"' and r.type = 0");
		Record borrowhistory = (Record)recordDao.getObject(statement);
		if(borrowhistory != null) {
			statement = new String("from User as u where u.userId = '"+borrowhistory.getUserId()+"'");
			User user = (User)userDao.getObject(statement);
			if(user != null)
				System.out.println(user.getUserId());
			recordDao.delete(borrowhistory);
			statement = new String("from Record as r where r.bookId = '"+book.getBookId()+"' and r.type = 1");
			Record reservehistory = (Record)recordDao.getObject(statement);
			if(reservehistory != null) {
				book.setFlag(4);
				bookDao.update(book);
				reservehistory.setFlag(2);
				SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date today = new Date();
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(today);	
				calendar.add(Calendar.DAY_OF_MONTH,1);
				Date endDay = calendar.getTime();
				reservehistory.setEndTime(sp.format(endDay));
				recordDao.update(reservehistory);
				statement = new String("from User as u where u.userId = '"+reservehistory.getUserId()+"'");
				User reserveuser = (User)userDao.getObject(statement);
				reserveMail reservemail = new reserveMail(reservehistory.getRecordId(),reserveuser.getEmail(),reserveuser.getUsername(),book.getBookName(),reservehistory.getEndTime(),1);
				this.bookDao.saveObject(reservemail);
				this.bookDao.update(reservemail);
			} else {
				book.setFlag(1);
				bookDao.update(book);
			}
			System.out.println("userflag");
			System.out.println(user.getFlag());
			if(user.getFlag() == 0) {
				statement = new String("from Record as r where r.userId = '"+borrowhistory.getUserId()+"' and r.type = 0 and r.flag = 0");
				System.out.println(borrowhistory.getUserId());
				List<Object> list = recordDao.getObjectList(statement);
				if(list == null || list.size() == 0) {
					user.setFlag(1);
					userDao.update(user);
				}
			}
			return "Success";
		} else {
			if(book == null)
				return "书籍不存在!";
			else if(borrowhistory == null)
				return "记录不存在!";
			return "fail!";
		}
	}
	@Override
	public List<Object> findallRecord() throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer("from Record order by r.beginTime");
		List<Object> list = this.recordDao.getObjectList(new String(queryString));
		return list;
	}
	@Override
	public List<Object> findAllBorrowRecord() throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer("from Record as r where r.type = 0 order by r.beginTime");
		List<Object> list = this.recordDao.getObjectList(new String(queryString));
		return list;
	}
	@Override
	public List<Object> findAllReserveRecord() throws HibernateException, InterruptedException {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer("from Record as r where r.type = 1 order by r.beginTime");
		List<Object> list = this.recordDao.getObjectList(new String(queryString));
		return list;
	}
	
}
