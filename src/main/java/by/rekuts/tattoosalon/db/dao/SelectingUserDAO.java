package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.SalonUser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SelectingUserDAO {
    private static final Logger LOGGER = LogManager.getLogger(SelectingUserDAO.class.getName());

    private final static String COLUMN_LABEL_USER_ID = "userid";
    private final static String COLUMN_LABEL_LOGIN = "login";
    private final static String COLUMN_LABEL_NAME = "name";
    private final static String COLUMN_LABEL_SURNAME = "surname";
    private final static String COLUMN_LABEL_EMAIL = "email";
    private final static String COLUMN_LABEL_GENDER = "gender";
    private final static String COLUMN_LABEL_ROLE = "userrole";
    private final static String COLUMN_LABEL_REGISTER = "register";
    private final static String COLUMN_LABEL_BIRTH = "birth";
    private final static String COLUMN_LABEL_BLOCKED = "blocked";
    private final static int USER_MASTER_ROLE_ID = 1;

    public static SalonUser loadPersonalData(String searchedLogin) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        SalonUser user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_USER_DATA.getQuery());
            preparedStatement.setString(1, searchedLogin);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(COLUMN_LABEL_USER_ID);
                String login = resultSet.getString(COLUMN_LABEL_LOGIN);
                String name = resultSet.getString(COLUMN_LABEL_NAME);
                String surname = resultSet.getString(COLUMN_LABEL_SURNAME);
                String email = resultSet.getString(COLUMN_LABEL_EMAIL);
                boolean gender = resultSet.getBoolean(COLUMN_LABEL_GENDER);
                int role = resultSet.getInt(COLUMN_LABEL_ROLE);
                LocalDateTime register = resultSet.getTimestamp(COLUMN_LABEL_REGISTER).toLocalDateTime();
                LocalDate birth = resultSet.getDate(COLUMN_LABEL_BIRTH).toLocalDate();
                boolean blocked = resultSet.getBoolean(COLUMN_LABEL_BLOCKED);
                user = new SalonUser(userId, login, name, surname, email, gender, role, register, birth, blocked);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't select user by his login. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return user;
    }

    /**Check masters from database to view them on masters.jsp page. */

    public static ArrayList<SalonUser> selectMasters() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<SalonUser> masters = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_MASTERS.getQuery());
            preparedStatement.setInt(1, USER_MASTER_ROLE_ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int masterId = resultSet.getInt(COLUMN_LABEL_USER_ID);
                String masterLogin = resultSet.getString(COLUMN_LABEL_LOGIN);
                String masterName = resultSet.getString(COLUMN_LABEL_NAME);
                String masterSurname = resultSet.getString(COLUMN_LABEL_SURNAME);
                SalonUser master = new SalonUser(masterId, masterLogin, masterName, masterSurname);
                masters.add(master);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't check user role. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return masters;
    }

    public static int selectSumAuthorsPublictions(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int publicationsSum = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_SUM_AUTHORS_PUBLICATION.getQuery());
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            publicationsSum = resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't count author's publications. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return publicationsSum;
    }

    public static ArrayList<SalonUser> selectAllUsers() {
        Connection connection = null;
        Statement statement;
        ResultSet resultSet;
        ArrayList<SalonUser> allUsers = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryToDatabase.SELECT_ALL_USERS.getQuery());
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_LABEL_USER_ID);
                String login = resultSet.getString(COLUMN_LABEL_LOGIN);
                String name = resultSet.getString(COLUMN_LABEL_NAME);
                String surname = resultSet.getString(COLUMN_LABEL_SURNAME);
                String email = resultSet.getString(COLUMN_LABEL_EMAIL);
                boolean male = resultSet.getBoolean(COLUMN_LABEL_GENDER);
                int userRole = resultSet.getInt(COLUMN_LABEL_ROLE);
                LocalDateTime register = resultSet.getTimestamp(COLUMN_LABEL_REGISTER).toLocalDateTime();
                LocalDate birth = resultSet.getTimestamp(COLUMN_LABEL_BIRTH).toLocalDateTime().toLocalDate();
                boolean blocked = resultSet.getBoolean(COLUMN_LABEL_BLOCKED);
                SalonUser salonUser = new SalonUser(id, login, name, surname, email, male, userRole, register, birth, blocked);
                allUsers.add(salonUser);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't select all publications in database. " + e);
        } finally {
            // display the latest news first
            allUsers.sort((o1, o2) -> o2.getRegistration().compareTo(o1.getRegistration()));
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return allUsers;
    }

}
