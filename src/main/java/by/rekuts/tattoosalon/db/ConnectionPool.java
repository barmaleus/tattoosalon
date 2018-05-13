package by.rekuts.tattoosalon.db;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool{
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    private ArrayDeque<Connection> pool = new ArrayDeque<>();
    private static final int MAX_CONNECTIONS = 6;
    private String url = "jdbc:mysql://localhost:3306/tattooparlor?useSSL=false&databaseName=tattooparlor&user=root&password=zxcvfrghj&useUnicode=true&characterEncoding=UTF-8";
    private final ReentrantLock getConnectionLock = new ReentrantLock();
    private final ReentrantLock returnConnectionLock = new ReentrantLock();

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARN, "Can't load mysql driver. ", e);
        }
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            pool.add(createNewConnection());
        }
    }

    private static ConnectionPool instance = null;
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createNewConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "SQL expeption while creating Connection. " , e);
        }
        return connection;
    }

    public Connection getConnection() {
        Connection connection = null;
        getConnectionLock.lock();
        try {
            if (pool.size() > 0) {
                connection = pool.poll(); //retrieves connection and removes it from pool begining. If query is empty, returns null
            }
        } finally {
            getConnectionLock.unlock();
        }
        return connection;
    }

    public void returnConnectionToPool(Connection connection) {
        returnConnectionLock.lock();
        try {
            pool.add(connection);
        } finally {
            returnConnectionLock.unlock();
        }
    }

    public void closeConnectionsInPool() {
        Connection connection;
        try {
            while(pool.size() > 0) {
                connection = pool.poll();
                connection.close();
            }
            LOGGER.log(Level.INFO, "Connections in pool have been closed.");
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Connections in pool haven't been closed. " , e);
        }

    }

}