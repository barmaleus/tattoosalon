package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        // destruction of a session
        request.getSession().invalidate();
        return page;
    }
}