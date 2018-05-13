package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.Publication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class PublicationDAO {
    private static final Logger LOGGER = LogManager.getLogger(PublicationDAO.class.getName());

    private final static String COLUMN_LABEL_PUBLICATION_ID = "id_publication";
    private final static String COLUMN_LABEL_TITLE = "title";
    private final static String COLUMN_LABEL_CONTENT = "content";
    private final static String COLUMN_LABEL_CONTENT_TYPE = "content_type";
    private final static String COLUMN_LABEL_AUTHOR = "author";
    private final static String COLUMN_LABEL_PUBLISH = "publish_time";
    private final static String COLUMN_LABEL_BLOCKED = "blocked";

    public static ArrayList<Publication> selectAllPublications() {
        Connection connection = null;
        Statement statement;
        ResultSet resultSet;
        ArrayList<Publication> publicationList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryToDatabase.SELECT_ALL_NEWS.getQuery());
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_LABEL_PUBLICATION_ID);
                String title = resultSet.getString(COLUMN_LABEL_TITLE);
                String content = resultSet.getString(COLUMN_LABEL_CONTENT);
                boolean textNotPhoto = resultSet.getBoolean(COLUMN_LABEL_CONTENT_TYPE);
                String author = resultSet.getString(COLUMN_LABEL_AUTHOR);
                LocalDateTime publishTime = resultSet.getTimestamp(COLUMN_LABEL_PUBLISH).toLocalDateTime();
                boolean blocked = resultSet.getBoolean(COLUMN_LABEL_BLOCKED);
                Publication publication = new Publication(id, title, content, textNotPhoto, author, publishTime, blocked);
                publicationList.add(publication);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't select all publications in database. " + e);
        } finally {
            //выводить в последние новости первыми
            publicationList.sort((o1, o2) -> o2.getPublishTime().compareTo(o1.getPublishTime()));
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return publicationList;
    }

    public static Publication selectPublicationById(int publicationId) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Publication publication = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_PUBLICATION_BY_ID.getQuery());
            preparedStatement.setInt(1, publicationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_LABEL_PUBLICATION_ID);
                String title = resultSet.getString(COLUMN_LABEL_TITLE);
                String content = resultSet.getString(COLUMN_LABEL_CONTENT);
                boolean textNotPhoto = resultSet.getBoolean(COLUMN_LABEL_CONTENT_TYPE);
                String author = resultSet.getString(COLUMN_LABEL_AUTHOR);
                LocalDateTime publishTime = resultSet.getTimestamp(COLUMN_LABEL_PUBLISH).toLocalDateTime();
                boolean blocked = resultSet.getBoolean(COLUMN_LABEL_BLOCKED);
                publication = new Publication(id, title, content, textNotPhoto, author, publishTime, blocked);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't select publication by ID in database. " + e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }

        return publication;
    }

    public static boolean insertNewPublication(Publication publication) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.INSERT_NEW_PUBLICATION.getQuery());
            preparedStatement.setString(1, publication.getTitle());
            preparedStatement.setString(2, publication.getContent());
            preparedStatement.setBoolean(3, publication.isTextNotPhoto());
            preparedStatement.setString(4, publication.getAuthor());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new publication to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }

    public static boolean makePublicationBlocked(int publicationId, boolean blockedParameter) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.UPDATE_PUBLICATION_BLOCKED.getQuery());
            preparedStatement.setBoolean(1, blockedParameter);
            preparedStatement.setInt(2, publicationId);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't change publication 'blocked' parameter. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }
}
