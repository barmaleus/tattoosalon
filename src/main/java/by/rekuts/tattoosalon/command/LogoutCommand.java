package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");

        // destruction of a session
        request.getSession().invalidate();
        return page;
    }
}
