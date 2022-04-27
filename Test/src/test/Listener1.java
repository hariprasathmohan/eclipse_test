package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class Listener1 implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("Hi");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("bye");
    }
	
}
