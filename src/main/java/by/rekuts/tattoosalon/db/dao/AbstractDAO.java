package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.exception.ConnectionToDBException;
import by.rekuts.tattoosalon.subject.SalonEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <K, T extends SalonEntity> { //todo this class is required???
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class.getName());

    public abstract List<T> findAll();
    public abstract T findEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Statement can't be closed.");
        }
    }

    public void close(Connection connection) throws ConnectionToDBException{
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionToDBException("Connection can't be closed", e);
        }
    }
}
