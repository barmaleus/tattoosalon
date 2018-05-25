package by.rekuts.tattoosalon.listeners;

import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> logins = (HashSet<String>)httpSessionEvent.getSession().getServletContext().getAttribute("logins");
        SalonUser sessionUser = (SalonUser) httpSessionEvent.getSession().getAttribute("salonUser");
        logins.remove(sessionUser.getLogin());
        sessionUser.setLoggedIn(false);
        httpSessionEvent.getSession().getServletContext().setAttribute("logins", logins);
    }
}
