package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class BlockUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_BLOCKED = "blocked";
    private static final int PARAM_ADMIN_ID = 0;

    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = (String) request.getSession().getAttribute(PARAM_NAME_USER);
        boolean flag;
        String page;
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        boolean blocked = Boolean.parseBoolean(request.getParameter(PARAM_NAME_BLOCKED));
        if (blocked) {
            flag = UserLogic.makeUserBlocked(userId, false);
        } else {
            flag = UserLogic.makeUserBlocked(userId, true);
        }
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
