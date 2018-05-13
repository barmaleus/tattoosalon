package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.SalonUser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckingUserDAO {
    private static final Logger LOGGER = LogManager.getLogger(CheckingUserDAO.class.getName());

    private final static String COLUMN_LABEL_LOGIN = "login";
    private final static String COLUMN_LABEL_PASSWORD = "password";
    private final static String COLUMN_LABEL_ROLE = "userrole";

    public static boolean checkLogin(String enterLogin, String encryptedPass) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String loginDB = null;
        String passwordDB = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.CHECK_LOGIN_AND_PASSWORD.getQuery());
            preparedStatement.setString(1, enterLogin);
            preparedStatement.setString(2, encryptedPass);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                loginDB = resultSet.getString(COLUMN_LABEL_LOGIN);
                passwordDB = resultSet.getString(COLUMN_LABEL_PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't check cridentials. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return enterLogin.equals(loginDB) && encryptedPass.equals(passwordDB);
    }

    public static int checkUserRole(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int role = SalonUser.UserRole.USER.ordinal();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.CHECK_USER_ROLE.getQuery());
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getInt(COLUMN_LABEL_ROLE);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't check user role. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return role;
    }

    public static boolean checkLogin(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int loginCoincidence = 1;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.CHECK_LOGIN.getQuery());
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            loginCoincidence = resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't check if login exist. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return loginCoincidence > 0;
    }

    public static boolean checkEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int emailCoincidence = 1;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.CHECK_EMAIL.getQuery());
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            emailCoincidence = resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't check if email exist. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return emailCoincidence > 0;
    }
}
