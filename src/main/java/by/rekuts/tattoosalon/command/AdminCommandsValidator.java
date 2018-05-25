package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminCommandsValidator {
    private static final int PARAM_ADMIN_ID = 0;

    static String adminCommandsValidator(HttpServletRequest request, boolean flag, String userLogin) {
        String page;
        if (flag && UserLogic.checkUserRole(userLogin) == PARAM_ADMIN_ID) {
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
