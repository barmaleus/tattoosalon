package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.logic.LoginLogic;
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
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass)) {
            HttpSession session = request.getSession();
            SalonUser user = UserLogic.loadPersonalData(login);
            session.setAttribute("salonUser", user);
            session.setAttribute("user", login);                                        //todo заменить на salonUser
            session.setAttribute("userRange", UserLogic.checkUserRole(login));                 //todo заменить на salonUser
            ArrayList<Publication> allPublications = PublicationLogic.viewAllUnblockedPublications();
            request.setAttribute("allPublications", allPublications);
            ListPage<Publication> results = new ListPage<>(allPublications, 0, allPublications.size(), 3);
            request.setAttribute("results", results);

            int fromIndex = results.getPage()*results.getMaxPerPage();
            int toIndex;
            if ((fromIndex + results.getMaxPerPage() >= allPublications.size())) {
                toIndex = allPublications.size();
            } else {
                toIndex = fromIndex + results.getMaxPerPage();
            }
            List<Publication> viewedPublications = allPublications.subList(fromIndex, toIndex);
            request.setAttribute("viewedPublications", viewedPublications);

            /** path to main.jsp*/
            page = ConfigurationManager.getProperty("path.page.command.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    } 
}
