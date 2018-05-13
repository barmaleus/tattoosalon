package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;

public class PageCabinetCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        SalonUser user = UserLogic.loadPersonalData(String.valueOf(request.getParameter("uname")));
        request.setAttribute("uname", user);
        request.setAttribute("publicationSum", UserLogic.selectSumAuthorsPublictions(user.getLogin()));
        return ConfigurationManager.getProperty("path.page.main");
    }
}
