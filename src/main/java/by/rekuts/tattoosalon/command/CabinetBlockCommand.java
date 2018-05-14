package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class CabinetBlockCommand implements ActionCommand{
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_USER_LOGIN = "uname";
    private static final String PARAM_NAME_BLOCKED = "blocked";
    private static final int PARAM_ADMIN_ID = 0;

    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = (String) request.getSession().getAttribute(PARAM_NAME_USER);
        boolean flag;
        String page;
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        String changingUser = request.getParameter(PARAM_NAME_USER_LOGIN);
        boolean blocked = Boolean.parseBoolean(request.getParameter(PARAM_NAME_BLOCKED));
        if (blocked) {
            flag = UserLogic.makeUserBlocked(userId, false);
        } else {
            flag = UserLogic.makeUserBlocked(userId, true);
        }
        if (flag && UserLogic.checkUserRole(userLogin) == PARAM_ADMIN_ID) {
            page = "/controller?command=cabinet&uname=" + changingUser;
        } else if (flag && changingUser.equals(userLogin)) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.youareblocked"));
            page = ConfigurationManager.getProperty("path.page.login");
            request.getSession().invalidate();
        } else {
            request.setAttribute("someErrorMessage", MessageManager.getProperty("message.norightsadmin"));
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
