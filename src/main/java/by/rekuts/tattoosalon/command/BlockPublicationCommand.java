package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import javax.servlet.http.HttpServletRequest;

public class BlockPublicationCommand implements ActionCommand {
@Override
public String execute(HttpServletRequest request) {
    String userLogin = (String) request.getSession().getAttribute("user");
    boolean flag;
    String page;
    int publicationId = Integer.parseInt(request.getParameter("publicationId"));
    boolean blocked = Boolean.parseBoolean(request.getParameter("blocked"));
    if (blocked) {
        flag = PublicationLogic.makePublicationBlocked(publicationId, false);
    } else {
        flag = PublicationLogic.makePublicationBlocked(publicationId, true);
    }
    page = AdminCommandsValidator.adminCommandsValidator(request, flag, userLogin);
    return page;
}
}
