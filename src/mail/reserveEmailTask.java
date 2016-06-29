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
					String title = rm.getUserName()+"ԤԼ"+rm.getBookName()+"���!";
					map.put("title", title);
					String content = "����ԤԼ"+rm.getBookName()+",���ǽ����鼮�黹ʱ�ʼ�֪ͨ��!";
					map.put("content", content);
				} else {
					String title = rm.getUserName()+"ԤԼ"+rm.getBookName()+"�ɹ�!";
					map.put("title", title);
					String content = "��ԤԼ��"+rm.getBookName()+"�Ѿ��黹,����24Сʱ��ǰ��ͼ��ݽ��ģ�����ԤԼ��ʧЧ!";
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
			 log.info("-------------������Ϣ�����쳣--------------");
			 System.out.println(e);
		 }
	}

}
