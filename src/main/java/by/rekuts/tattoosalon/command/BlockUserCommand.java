package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.exception.SalonException;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_BLOCKED = "blocked";

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
        try {
            page = AdminCommandsValidator.adminCommandsValidator(request, flag, userLogin);
        } catch (SalonException e) {
            request.setAttribute("someErorMessage", MessageManager.getProperty("message.norightsadmin"));
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
