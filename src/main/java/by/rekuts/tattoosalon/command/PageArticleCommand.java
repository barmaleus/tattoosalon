package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.ListPage;
import by.rekuts.tattoosalon.subject.Publication;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PageArticleCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        ArrayList<Publication> articlePublications = PublicationLogic.viewArticlePublications();

        ListPage<Publication> results = new ListPage<>(articlePublications, Integer.parseInt(request.getParameter("page")), articlePublications.size(), 3);
        request.setAttribute("results", results);

        int fromIndex = results.getPage()*results.getMaxPerPage();
        int toIndex;
        if ((fromIndex + results.getMaxPerPage() >= articlePublications.size())) {
            toIndex = articlePublications.size();
        } else {
            toIndex = fromIndex + results.getMaxPerPage();
        }
        List<Publication> viewedPublications = articlePublications.subList(fromIndex, toIndex);
        request.setAttribute("viewedPublications", viewedPublications);

        return ConfigurationManager.getProperty("path.page.main");
    }
}
