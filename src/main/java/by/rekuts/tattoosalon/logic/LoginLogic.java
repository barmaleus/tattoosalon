package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.dao.CheckingUserDAO;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass) {
        String encryptedPass = EncryptionLogic.hashPassword(enterPass);
        return CheckingUserDAO.checkLogin(enterLogin, encryptedPass);
    }
}
