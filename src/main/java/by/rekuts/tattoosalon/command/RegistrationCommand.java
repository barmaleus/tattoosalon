package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.RegistrationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_NAME_REG_LOGIN = "reg-login";
    private static final String PARAM_NAME_REG_PASSWORD = "reg-password";
    private static final String PARAM_NAME_REG_NAME = "reg-name";
    private static final String PARAM_NAME_REG_SURNAME = "reg-surname";
    private static final String PARAM_NAME_REG_EMAIL = "reg-email";
    private static final String PARAM_NAME_REG_SEX = "reg-sex";
    private static final String PARAM_NAME_REG_BIRTH = "reg-birth";

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        SalonUser user = new SalonUser();
        user.setLogin(request.getParameter(PARAM_NAME_REG_LOGIN));
        user.setPassword(request.getParameter(PARAM_NAME_REG_PASSWORD));
        user.setName(request.getParameter(PARAM_NAME_REG_NAME));
        user.setSurname(request.getParameter(PARAM_NAME_REG_SURNAME));
        user.setEmail(request.getParameter(PARAM_NAME_REG_EMAIL));
        user.setMale(Boolean.valueOf(request.getParameter(PARAM_NAME_REG_SEX)));
        user.setBirth(LocalDate.parse(request.getParameter(PARAM_NAME_REG_BIRTH)));

        if(!RegistrationLogic.checkLogin(user.getLogin()) && !RegistrationLogic.checkEmail(user.getEmail())) {
            RegistrationLogic.insertUser(user);
            page = ConfigurationManager.getProperty("path.page.createuser");        //new page
        } else if (RegistrationLogic.checkLogin(user.getLogin())){
            request.setAttribute("errorLoginExists", MessageManager.getProperty("message.errorloginexists"));
            page = ConfigurationManager.getProperty("path.page.registration");
        } else {
            request.setAttribute("errorEmailExists", MessageManager.getProperty("message.erroremailexists"));
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}
