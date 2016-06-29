package mail;
import java.util.*;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.LMS.model.daoImpl.BaseDaoImpl;
import com.LMS.model.daoImpl.registerMailDaoImpl;

public class emailNoticeManager {
	private static final long PERIOD_DAY = 3*60*1000; // 3����
	private BaseDaoImpl dao;
	public emailNoticeManager(ServletContextEvent event) {
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext()); 
		dao = (BaseDaoImpl)context.getBean("baseDao"); 
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);  
		Date date=calendar.getTime(); //��һ��ִ�ж�ʱ�����ʱ�� 
		//�����һ��ִ�ж�ʱ�����ʱ�� С�� ��ǰ��ʱ��
		//��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�ѭ��ִ�е��������Ե�ǰʱ��Ϊ׼
		if (date.before(new Date())) {
			//date = this.addDay(date, 1);
		}
		calendar.setTime(new Date());
		date = calendar.getTime();
		
		Timer timer1 = new Timer();  
		registerEmailTask task = new registerEmailTask(dao);
		//����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
		timer1.schedule(task,date,PERIOD_DAY);
		
		Timer timer2 = new Timer();
		reserveEmailTask task2 = new reserveEmailTask(dao);
		timer2.schedule(task2, this.addMin(date,1), PERIOD_DAY);
		
		Timer timer3 = new Timer();
		obsoleteEmailTask task3 = new obsoleteEmailTask(dao);
		timer3.schedule(task3, this.addMin(date,2), PERIOD_DAY);
	 }


	// ���ӻ��������
	public Date addDay(Date date, int num) {
	  	Calendar startDT = Calendar.getInstance();
	  	startDT.setTime(date);
	  	startDT.add(Calendar.DAY_OF_MONTH, num);
	  	return startDT.getTime();
	  	}
	// ���ӻ���ٷ���
	public Date addMin(Date date, int num) {
	  	Calendar startDT = Calendar.getInstance();
	  	startDT.setTime(date);
	  	startDT.add(Calendar.MINUTE, num);
	  	return startDT.getTime();
	}
}
