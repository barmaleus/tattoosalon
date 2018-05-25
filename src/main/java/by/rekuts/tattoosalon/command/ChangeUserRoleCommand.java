package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.UserLogic;
import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements ActionCommand {
    private static final String PARAM_NAME_USER = "user";
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
        String userLogin = (String) request.getSession().getAttribute(PARAM_NAME_USER);
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
        page = AdminCommandsValidator.adminCommandsValidator(request, flag, userLogin);
        return page;
    }
}
