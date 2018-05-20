package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.SalonUser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatingUserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UpdatingUserDAO.class.getName());

    public static boolean insertNewUser(SalonUser user) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.INSERT_NEW_USER.getQuery());
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setBoolean(6, user.isMale());
            preparedStatement.setDate(7, Date.valueOf(user.getBirth()));
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new user to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }

    public static boolean makeUserBlocked(int userId, boolean blockedParameter) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.UPDATE_USER_BLOCKED.getQuery());
            preparedStatement.setBoolean(1, blockedParameter);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't change user 'blocked' parameter. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }

    public static boolean changeUserRole(int userId, int userRoleId) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.UPDATE_USER_ROLE.getQuery());
            preparedStatement.setInt(1, userRoleId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't change user 'userRole' parameter. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }
}
