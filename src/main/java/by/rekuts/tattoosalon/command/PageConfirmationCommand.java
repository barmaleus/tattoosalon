package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;

import javax.servlet.http.HttpServletRequest;

public class PageConfirmationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String publicationTitle = null;
        String publicationText = null;
        try {
            publicationTitle = request.getParameter("title");
            publicationText = request.getParameter("text");
        } catch (NullPointerException e) {
            //Logging NullPointerException has no sense, couse it means that this command is requested from AddPortfolioPage
        }
        if (publicationText != null) {  //it means that publication is article, because parameter 'text' exist only on article creating page
            boolean flag;
            final String author = request.getSession().getAttribute("user").toString();
            final boolean textNotPhoto = true;
            final Publication article = new Publication(publicationTitle, publicationText, textNotPhoto, author);
            flag = PublicationLogic.insertNewPublication(article);
            if (flag) {
                request.setAttribute("message", MessageManager.getProperty("message.article.add.ok"));
            } else {
                request.setAttribute("message", MessageManager.getProperty("message.article.add.bad"));
            }
            return ConfigurationManager.getProperty("path.page.main");
        } else {
            return ConfigurationManager.getProperty("path.page.main");
        }

    }
}
