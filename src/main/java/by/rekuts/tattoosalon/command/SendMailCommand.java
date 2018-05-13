package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.mail.MailThread;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

public class SendMailCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(SendMailCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        Properties properties = new Properties();
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        String fileName = context.getInitParameter("mail");
// загрузка параметров почтового сервера в объект свойств
        try {
            properties.load(context.getResourceAsStream(fileName));
        } catch (IOException e) {
            LOGGER.log(Level.WARN, "Properties couldn't be load");
        }
        MailThread mailOperator = new MailThread(session.getAttribute("user").toString(), request.getParameter("subject"), request.getParameter("body"),properties);
        // TODO attribute user could be "" if user is out of session - validate this
        // запуск процесса отправки письма в отдельном потоке
        mailOperator.start();
// переход на страницу с предложением о создании нового письма
        page = ConfigurationManager.getProperty("path.page.send");
        return page;
    }
}




