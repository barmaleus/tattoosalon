package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.LoginLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.ListPage;
import by.rekuts.tattoosalon.subject.Publication;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final int MAX_INACTIVE_INTERVAL_OF_SESSION_SECS = 180;
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.checkLogin(login, pass)) {   //check credentials
            SalonUser user = UserLogic.loadPersonalData(login);
            if(!user.isBlocked()) {    //check for user blocking or deleting in system
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL_OF_SESSION_SECS);
                session.setAttribute("salonUser", user);
                if (!user.isLoggedIn()) {   //check for user logged in in another browser or device
                    session.setAttribute("salonUser", user);
                    ArrayList<Publication> allPublications = PublicationLogic.viewAllUnblockedPublications();
                    ListPage<Publication> results = new ListPage<>(allPublications, 0, allPublications.size(), 3);
                    request.setAttribute("results", results);

                    //First and last indexes of viewed publications for pager
                    int fromIndex = results.getPage()*results.getMaxPerPage();
                    int toIndex;
                    if ((fromIndex + results.getMaxPerPage() >= allPublications.size())) {
                        toIndex = allPublications.size();
                    } else {
                        toIndex = fromIndex + results.getMaxPerPage();
                    }
                    List<Publication> viewedPublications = allPublications.subList(fromIndex, toIndex);
                    request.setAttribute("viewedPublications", viewedPublications);
                    user.setLoggedIn(true);

                    /* path to main.jsp*/
                    page = ConfigurationManager.getProperty("path.page.command.main");
                } else {
                    session.removeAttribute("salonUser");
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.alreadylogged"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }   // end of check for user logged in in another browser or device
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginblocked"));
                page = ConfigurationManager.getProperty("path.page.login");
            }   //end of check for user blocking or deleting in system

        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }   // end of check credentials
        return page;
    } 
}
