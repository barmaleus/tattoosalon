package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class PagePublicationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("publication", PublicationLogic.viewPublicationById(Integer.parseInt(request.getParameter("pub"))));
        return ConfigurationManager.getProperty("path.page.main");
    }
}
