package by.rekuts.tattoosalon.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(MailThread.class.getName());
    private static final String SEND_TO_EMAIL = "superolegash@yandex.ru";

    private MimeMessage message;
    private String userName;
    private String mailSubject;
    private String mailText;
    private Properties properties;
    public MailThread(String userName, String mailSubject, String mailText, Properties properties) {
        this.userName = userName;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }
    private void init() {
//объект почтовой сессии
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
// создание объекта почтового сообщения
        message = new MimeMessage(mailSession);
        try
        {
// загрузка параметров в объект почтового сообщения
            message.setSubject(userName + ", tattoparlor site: " + mailSubject);
            message.setContent(mailText,"text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(SEND_TO_EMAIL));
            System.out.println(properties.stringPropertyNames());
        }
        catch (AddressException e) {
            LOGGER.log(Level.WARN, "Adress is incorrect " + SEND_TO_EMAIL + " " + e);
        }
        catch (MessagingException e) {
            LOGGER.log(Level.WARN, "Exception in creating message " + e);
        }
    }
    public void run() {
        init();
        try
        {
// отправка почтового сообщения
            Transport.send(message);
        }
        catch (MessagingException e) {
            LOGGER.log(Level.WARN, "Exception in sending message " + e);
        }
    }
}