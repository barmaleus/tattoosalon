package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
    /*if there are error or empty command, redirect to login page */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
