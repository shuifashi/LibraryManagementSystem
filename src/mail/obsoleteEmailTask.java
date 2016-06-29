package mail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.LMS.model.beans.*;
import com.LMS.model.daoImpl.BaseDaoImpl;

public class obsoleteEmailTask extends TimerTask{
	private static Logger log = Logger.getLogger(registerEmailTask.class.toString());
	private BaseDaoImpl dao;
	public obsoleteEmailTask(BaseDaoImpl dao) {
		this.dao = dao;
	}
	public synchronized void sendEmail() {
		List<HashMap<String,String>> mailList = new ArrayList<HashMap<String,String>>();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date today = new Date();
		String statement= "from Record as r where r.type = 0 and r.endTime < '" + sp.format(today)+"'";
		List<Object> list = dao.getObjectList(statement);
		if(list != null &&!list.isEmpty()) {
			for(Object o : list) {
				HashMap<String,String> map = new HashMap<String,String>();
				Record record = (Record)o;
				statement = "from Book as b where b.bookId = '" + record.getBookId() +"'";
				Book book = (Book)dao.getObject(statement);
				statement = "from User as u where u.userId = '" + record.getUserId() +"'";
				User user = (User)dao.getObject(statement);
				user.setFlag(0);
				dao.update(user);
				record.setFlag(0);
				dao.update(record);
				map.put("receiver", user.getEmail());
				String title = "《"+book.getBookName()+"》已经超期!";
				map.put("title", title);
				String content = user.getUsername() + "您借出的图书《"+book.getBookName()+"》已经在 "+ record.getEndTime()+" 超期! 我们将暂停您的借书权限直到图书归还!";
				map.put("content", content);
				mailList.add(map);
			}
		}
		statement= "from Record as r where r.type = 1 and r.flag = 2 and r.endTime < '" + sp.format(today)+"'";
		list = dao.getObjectList(statement);
		if(list != null && !list.isEmpty()) {
			for(Object o : list) {
				HashMap<String,String> map = new HashMap<String,String>();
				Record record = (Record)o;
				statement = "from Book as b where b.bookId = '" + record.getBookId() +"'";
				Book book = (Book)dao.getObject(statement);
				statement = "from User as u where u.userId = '" + record.getUserId() +"'";
				User user = (User)dao.getObject(statement);
				book.setFlag(1);
				dao.update(book);
				dao.delete(record);
				map.put("receiver", user.getEmail());
				String title = "《"+book.getBookName()+"》预约已经超期!";
				map.put("title", title);
				String content = user.getUsername() + "您预约的图书《"+book.getBookName()+"》已经在 "+ record.getEndTime()+" 超期! 该次预约失效!";
				map.put("content", content);
				mailList.add(map);
			}
		}
		if(mailList.size() != 0) {
			mailSender mail = new mailSender(mailList);
			mail.run();
		}
	    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Date day = new Date();
			System.out.println(day.toString());
			System.out.println("obsolete email begin");
			sendEmail();
			System.out.println("obsolete email end");
		 } catch (Exception e) {
			 log.info("-------------解析信息发生异常--------------");
			 System.out.println(e);
		 }
	}
}
