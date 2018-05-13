package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.dao.CheckingUserDAO;
import by.rekuts.tattoosalon.db.dao.UpdatingUserDAO;
import by.rekuts.tattoosalon.subject.SalonUser;

public class RegistrationLogic {
    public static boolean insertUser(SalonUser user) {
        String userPassword = user.getPassword();
        String encryptedPass = EncryptionLogic.hashPassword(userPassword);
        user.setPassword(encryptedPass);
        return UpdatingUserDAO.insertNewUser(user);
    }

    public static boolean checkLogin(String login) {
        return CheckingUserDAO.checkLogin(login);
    }

    public static boolean checkEmail(String email) {
        return CheckingUserDAO.checkEmail(email);
    }
}


