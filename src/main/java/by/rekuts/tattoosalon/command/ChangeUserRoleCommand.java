package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.exception.SalonException;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements ActionCommand {
    private static final String PARAM_NAME_USER = "salonUser";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_OPERATION = "operation";
    private static final String MAKE_USER_OPERATION = "makeUser";
    private static final String MAKE_MASTER_OPERATION = "makeMaster";
    private static final String MAKE_ADMIN_OPERATION = "makeAdmin";
    private static final int PARAM_USER_ID = 2;
    private static final int PARAM_MASTER_ID = 1;
    private static final int PARAM_ADMIN_ID = 0;

    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = ((SalonUser)request.getSession().getAttribute(PARAM_NAME_USER)).getLogin();
        boolean flag = false;
        String page;
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        String operation = request.getParameter(PARAM_NAME_OPERATION);

        switch (operation) {
            case MAKE_USER_OPERATION:
                flag = UserLogic.changeUserRole(userId, PARAM_USER_ID);
                break;
            case MAKE_MASTER_OPERATION:
                flag = UserLogic.changeUserRole(userId, PARAM_MASTER_ID);
                break;
            case MAKE_ADMIN_OPERATION:
                flag = UserLogic.changeUserRole(userId, PARAM_ADMIN_ID);
                break;
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
