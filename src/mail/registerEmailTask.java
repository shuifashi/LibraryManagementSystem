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
public class registerEmailTask extends TimerTask {
	private static Logger log = Logger.getLogger(registerEmailTask.class.toString());
	private BaseDaoImpl dao;
	public registerEmailTask(BaseDaoImpl dao) {
		this.dao = dao;
	}
	public synchronized void sendEmail() {
		String statement= "from registerMail";
		List<Object> list = dao.getObjectList(statement);
		if(!list.isEmpty()) {
			System.out.println(list.size());
			System.out.println(((registerMail)list.get(0)).getUsername());
			List<HashMap<String,String>> mailList = new ArrayList<HashMap<String,String>>();
			for(Object o : list) {
				HashMap<String,String> map = new HashMap<String,String>();
				registerMail rm = (registerMail)o;
				map.put("receiver", rm.getEmail());
				String title = rm.getUsername()+"注册成功!";
				map.put("title", title);
				String content = "恭喜"+rm.getUsername()+"注册成功!";
				map.put("content", content);
				mailList.add(map);
				dao.delete(o);
			}
			mailSender mail = new mailSender(mailList);
			mail.run();
		}
	    }
	 @Override
	public void run() {
		 try {
			Date day = new Date();
			System.out.println(day.toString());
			System.out.println("register email begin");
			sendEmail();
			System.out.println("register email end");
		 } catch (Exception e) {
			 log.info("-------------解析信息发生异常--------------");
			 System.out.println(e);
		 }
	 }
}
