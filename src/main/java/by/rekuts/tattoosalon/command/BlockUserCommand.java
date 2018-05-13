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
    private static final String PARAM_NAME_DELETING_USER = "uname";
    private static final String PARAM_NAME_COMMAND = "command";
    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = (String) request.getSession().getAttribute(PARAM_NAME_USER);
        boolean flag = false;
        String page;
        int userId = Integer.parseInt(request.getParameter("userId"));
        boolean blocked = Boolean.parseBoolean(request.getParameter("blocked"));
        if (blocked) {
            flag = UserLogic.makeUserBlocked(userId, false);
        } else {
            flag = UserLogic.makeUserBlocked(userId, true);
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


//        String page = null;
//        HttpSession session = request.getSession();
//        String user = session.getAttribute(PARAM_NAME_USER).toString();
//        String blockedUser = request.getParameter(PARAM_NAME_DELETING_USER);
//        String command = request.getParameter(PARAM_NAME_COMMAND);
//        if (PARAM_NAME_USER == PARAM_NAME_DELETING_USER || UserLogic.checkUserRole(user)) {
//            UserLogic.deleteUser(blockedUser);
//            page = request.getRequestURL().toString();
//        } else {
//            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
//            page = ConfigurationManager.getProperty("path.page.login");
////        } todo продолжить
//        return page;



    }
}
