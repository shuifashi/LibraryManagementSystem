package mail;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;
import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.LMS.model.beans.*;
import com.LMS.model.daoImpl.*;

public class reserveEmailTask extends TimerTask {
	private static Logger log = Logger.getLogger(registerEmailTask.class.toString());
	private BaseDaoImpl dao;
	public reserveEmailTask(BaseDaoImpl dao) {
		this.dao = dao;
	}
	public synchronized void sendEmail() {
		String statement= "from reserveMail";
		List<Object> list = dao.getObjectList(statement);
		if(!list.isEmpty()) {
			System.out.println(list.size());
			System.out.println(((reserveMail)list.get(0)).getUserName());
			List<HashMap<String,String>> mailList = new ArrayList<HashMap<String,String>>();
			for(Object o : list) {
				HashMap<String,String> map = new HashMap<String,String>();
				reserveMail rm = (reserveMail)o;
				map.put("receiver", rm.getEmail());
				if(rm.getFlag() == 0) {
					String title = rm.getUserName()+"预约"+rm.getBookName()+"完成!";
					map.put("title", title);
					String content = "您已预约"+rm.getBookName()+",我们将在书籍归还时邮件通知您!";
					map.put("content", content);
				} else {
					String title = rm.getUserName()+"预约"+rm.getBookName()+"成功!";
					map.put("title", title);
					String content = "您预约的"+rm.getBookName()+"已经归还,请在24小时内前往图书馆借阅，否则预约将失效!";
					map.put("content", content);
				}
				mailList.add(map);
				dao.delete(o);
			}
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
			System.out.println("reserve email begin");
			sendEmail();
			System.out.println("reserve email end");
		 } catch (Exception e) {
			 log.info("-------------解析信息发生异常--------------");
			 System.out.println(e);
		 }
	}

}
