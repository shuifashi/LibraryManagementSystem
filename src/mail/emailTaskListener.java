package mail;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class emailTaskListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		new emailNoticeManager(event);
	}
	public void contextDestroyed(ServletContextEvent event) {}
}
