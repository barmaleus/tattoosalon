package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.dao.CheckingUserDAO;
import by.rekuts.tattoosalon.db.dao.SelectingUserDAO;
import by.rekuts.tattoosalon.db.dao.UpdatingUserDAO;
import by.rekuts.tattoosalon.subject.SalonUser;
import by.rekuts.tattoosalon.subject.Publication;

import java.util.ArrayList;

public class UserLogic {
    public static SalonUser loadPersonalData(String login) {
        return SelectingUserDAO.loadPersonalData(login);
    }

    public static int checkUserRole(String login) {
        return CheckingUserDAO.checkUserRole(login);
    }

    public static ArrayList<SalonUser> selectMasters() {
        return SelectingUserDAO.selectMasters();
    }

    public static ArrayList<Publication> selectMastersPortfolio(String mastersLogin) {
        ArrayList<Publication> mastersPortfolio = new ArrayList<>();
        ArrayList<Publication> gallery = PublicationLogic.viewGalleryPublications();
        for(Publication publication : gallery) {
            if (publication.getAuthor().equals(mastersLogin)) {
                mastersPortfolio.add(publication);
            }
        }
        return mastersPortfolio;
    }

    public static int selectSumAuthorsPublictions(String login) {
        return SelectingUserDAO.selectSumAuthorsPublictions(login);
    }

    public static ArrayList<SalonUser> selectAllUsers() {
        return SelectingUserDAO.selectAllUsers();
    }

    public static boolean makeUserBlocked(int userId, boolean blockedParameter) {
        return UpdatingUserDAO.makeUserBlocked(userId, blockedParameter);
    }

    public static boolean changeUserRole(int userId, int userRoleId) {
        return UpdatingUserDAO.changeUserRole(userId, userRoleId);
    }
}
