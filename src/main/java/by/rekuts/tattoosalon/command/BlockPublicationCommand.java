package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BlockPublicationCommand implements ActionCommand {
@Override
public String execute(HttpServletRequest request) {
    String userLogin = (String) request.getSession().getAttribute("user");
    boolean flag = false;
    String page;
    int publicationId = Integer.parseInt(request.getParameter("publicationId"));
    boolean blocked = Boolean.parseBoolean(request.getParameter("blocked"));
    if (blocked) {
        flag = PublicationLogic.makePublicationBlocked(publicationId, false);
    } else {
        flag = PublicationLogic.makePublicationBlocked(publicationId, true);
    }

    if (flag && UserLogic.checkUserRole(userLogin) == 0) {
        ArrayList<Publication> viewedPublications = PublicationLogic.viewAllPublications();
        request.setAttribute("viewedPublications", viewedPublications);
        ArrayList<SalonUser> allUsers = UserLogic.selectAllUsers();
        request.setAttribute("allUsers", allUsers);
        page = ConfigurationManager.getProperty("path.page.admin");
    } else {
        request.setAttribute("someErorMessage", MessageManager.getProperty("message.norightsadmin"));
        page = ConfigurationManager.getProperty("path.page.error");
    }
    return page;
}
}
