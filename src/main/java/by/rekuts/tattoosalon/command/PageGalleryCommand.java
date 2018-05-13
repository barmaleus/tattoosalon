package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.PublicationLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.ListPage;
import by.rekuts.tattoosalon.subject.Publication;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PageGalleryCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String master = request.getParameter("master");
        ArrayList<Publication> galleryPublications;
        if(master != null && master != "") {
            galleryPublications = UserLogic.selectMastersPortfolio(master);
        } else {
            galleryPublications = PublicationLogic.viewGalleryPublications();
        }

        ListPage<Publication> results = new ListPage<>(galleryPublications, Integer.parseInt(request.getParameter("page")), galleryPublications.size(), 3);
        request.setAttribute("results", results);

        int fromIndex = results.getPage()*results.getMaxPerPage();
        int toIndex;
        if ((fromIndex + results.getMaxPerPage() >= galleryPublications.size())) {
            toIndex = galleryPublications.size();
        } else {
            toIndex = fromIndex + results.getMaxPerPage();
        }
        List<Publication> viewedPublications = galleryPublications.subList(fromIndex, toIndex);
        request.setAttribute("viewedPublications", viewedPublications);

        return ConfigurationManager.getProperty("path.page.main");
    }
}
