package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToRegistrationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.registration");
    }
}

