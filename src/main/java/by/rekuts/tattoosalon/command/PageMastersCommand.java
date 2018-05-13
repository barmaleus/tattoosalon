package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class PageMastersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("masters", UserLogic.selectMasters());
        return ConfigurationManager.getProperty("path.page.main");
    }

}
