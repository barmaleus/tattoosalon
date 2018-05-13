package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.dao.PublicationDAO;
import by.rekuts.tattoosalon.subject.Publication;

import java.util.ArrayList;

public class PublicationLogic {
    public static ArrayList<Publication> viewAllUnblockedPublications() {
        ArrayList<Publication> allUnblockedPublications = PublicationDAO.selectAllPublications();
        for (int i = 0; i < allUnblockedPublications.size(); ) {
            if (allUnblockedPublications.get(i).isBlocked()) {
                allUnblockedPublications.remove(i);
            } else {
                i++;
            }
        }
        return allUnblockedPublications;
    }

    public static ArrayList<Publication> viewAllPublications() {
        return PublicationDAO.selectAllPublications();
    }

    public static ArrayList<Publication> viewArticlePublications() {
        ArrayList<Publication> articles = PublicationDAO.selectAllPublications();
        for(int i = 0; i < articles.size(); ) {
            if (!articles.get(i).isTextNotPhoto()) {
                articles.remove(articles.remove(i));
            } else {
                i++;
            }
        }
        return articles;
    }

    public static ArrayList<Publication> viewGalleryPublications() {
        ArrayList<Publication> gallery = PublicationDAO.selectAllPublications();
        for(int i = 0; i < gallery.size(); ) {
            if (gallery.get(i).isTextNotPhoto()) {
                gallery.remove(i);
            } else {
                i++;
            }
        }
        return gallery;
    }

    public static Publication viewPublicationById(int publicationId) {
        Publication publication = PublicationDAO.selectPublicationById(publicationId);
        return publication;
    }

    public static boolean insertNewPublication(Publication publication) {
        return PublicationDAO.insertNewPublication(publication);
    }

    public static boolean makePublicationBlocked(int publicationId, boolean blockedParameter) {
        return PublicationDAO.makePublicationBlocked(publicationId, blockedParameter);
    }
}
