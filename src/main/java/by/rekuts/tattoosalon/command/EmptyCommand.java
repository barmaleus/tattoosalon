package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
    /*в случае ошибки или прямого обращения к контроллеру
    * переадресация на страницу ввода логина */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
