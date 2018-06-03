package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.exception.SalonException;
import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;

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
    try {
        page = AdminCommandsValidator.adminCommandsValidator(request, flag, userLogin);
    } catch (SalonException e) {
        request.setAttribute("someErorMessage", MessageManager.getProperty("message.norightsadmin"));
        page = ConfigurationManager.getProperty("path.page.error");
    }
    return page;
}
}
