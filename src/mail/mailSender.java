package mail;
import java.util.ArrayList;
import java.util.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.LMS.model.beans.registerMail;
public class mailSender implements Runnable{
	List<HashMap<String,String>> ls;
	public mailSender(List<HashMap<String,String>> ls) {
		this.ls = ls;
	}
	public MimeMessage createSimpleMail(Session session
			,String receiver,String title,String content) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("myownsky_mail@sina.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		// 邮件的标题
		message.setSubject(title);
		// 邮件的文本内容
		message.setContent(content, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	public void sendMail() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", "smtp.sina.cn");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			// 使用JavaMail发送邮件的5个步骤
			// 1、创建session
			Session session = Session.getInstance(prop);
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			session.setDebug(true);
			// 2、通过session得到transport对象
			Transport ts = session.getTransport();
			// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
			ts.connect("smtp.sina.cn", "myownsky_mail@sina.com", "myownsky");
			for(HashMap<String,String> m : ls) {
				// 4、创建邮件
				Message message = createSimpleMail(session,m.get("receiver"),m.get("title"),m.get("content"));
				// 5、发送邮件
				ts.sendMessage(message, message.getAllRecipients());
			}
			ts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendMail();
	}
}
