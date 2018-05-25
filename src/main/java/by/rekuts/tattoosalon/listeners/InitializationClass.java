package by.rekuts.tattoosalon.listeners;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
public class InitializationClass implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(InitializationClass.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "Setting initialization parameters ('logins' param)");
        HashSet<String> userLogins = new HashSet<>();
        ServletContext sc = servletContextEvent.getServletContext();
        sc.setAttribute("logins", userLogins);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "Context is destroyed");
    }
}
