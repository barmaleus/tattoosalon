package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class PageAdminCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = (String) request.getSession().getAttribute("user");
        if(UserLogic.checkUserRole(userLogin) == 0) {
            ArrayList<Publication> viewedPublications = PublicationLogic.viewAllPublications();
            request.setAttribute("viewedPublications", viewedPublications);
            ArrayList<SalonUser> allUsers = UserLogic.selectAllUsers();
            request.setAttribute("allUsers", allUsers);
            return ConfigurationManager.getProperty("path.page.admin");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.norightsadmin"));
            String page = ConfigurationManager.getProperty("path.page.login");
            request.getSession().invalidate();
            return page;
        }

    }
}
