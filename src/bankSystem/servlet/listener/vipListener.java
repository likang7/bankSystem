package bankSystem.servlet.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class vipListener
 *
 */
@WebListener
public class vipListener implements ServletContextListener {
	Timer timer = new Timer();
    /**
     * Default constructor. 
     */
    public vipListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	long daytime = 24 * 60 *60 *1000;
    	timer.schedule(new vipCheckTask(), new Date(), daytime);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	timer.cancel();
    }
	
}
