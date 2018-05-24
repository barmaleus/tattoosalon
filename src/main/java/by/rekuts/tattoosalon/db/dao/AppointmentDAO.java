package by.rekuts.tattoosalon.db.dao;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.Appointment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentDAO.class.getName());

    private final static String COLUMN_LABEL_APPOINTMENT_ID = "appointment_id";
    private final static String COLUMN_LABEL_APPOINTMENT_TYPE = "appointment_type";
    private final static String COLUMN_LABEL_MASTER_ID = "master_id";
    private final static String COLUMN_LABEL_CLIENT_ID = "client_id";
    private final static String COLUMN_LABEL_BEGINING_TIME = "begining_time";
    private final static String COLUMN_LABEL_ENDING_TIME = "ending_time";
    private final static String COLUMN_LABEL_ORDERING_TIME = "ordering_time";
    private final static String COLUMN_LABEL_STATUS_ID = "status";

    public static boolean insertAppointment(Appointment appointment) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.INSERT_NEW_APPOINTMENT.getQuery());
            preparedStatement.setInt(1, appointment.getAppointmentType());
            preparedStatement.setInt(2, appointment.getMasterId());
            preparedStatement.setInt(3, appointment.getClientId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(appointment.getBeginOfAppointment()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getEndOfAppointment()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getTimeOfOrder()));
            preparedStatement.setInt(7, appointment.getAppointmentStatus());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new appointment to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return flag;
    }

    public static ArrayList<Appointment> selectAppointmentsByMasterId(int masterId) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_APPOINTMENTS_BY_MASTER_ID.getQuery());
            preparedStatement.setInt(1, masterId);
            executeAppointmentResultSet(preparedStatement, appointments);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new appointment to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return appointments;
    }

    public static ArrayList<Appointment> selectAppointmentsByMasterIdForNext14Days(int masterId) {      //14 days - planning period when users may order the consultation
        Connection connection = null;
        PreparedStatement preparedStatement;
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_APPOINTMENTS_BY_MASTER_ID_FOR_NEX_14_DAYS.getQuery());
            preparedStatement.setInt(1, masterId);
            executeAppointmentResultSet(preparedStatement, appointments);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new appointment to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return appointments;
    }

    public static ArrayList<Appointment> selectAppointmentsByClientIdForNext14Days(int clientId) {      //14 days - planning period when users may order the consultation
        Connection connection = null;
        PreparedStatement preparedStatement;
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(QueryToDatabase.SELECT_APPOINTMENTS_BY_CLIENT_ID_FOR_NEX_14_DAYS.getQuery());
            preparedStatement.setInt(1, clientId);
            executeAppointmentResultSet(preparedStatement, appointments);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Can't insert new appointment to database. ", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnectionToPool(connection);
            }
        }
        return appointments;
    }

    private static void executeAppointmentResultSet(PreparedStatement preparedStatement, ArrayList<Appointment> appointments) throws SQLException{
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt(COLUMN_LABEL_APPOINTMENT_ID);
            int appointmentType = resultSet.getInt(COLUMN_LABEL_APPOINTMENT_TYPE);
            int appointmentMasterId = resultSet.getInt(COLUMN_LABEL_MASTER_ID);
            int appointmetnClientId = resultSet.getInt(COLUMN_LABEL_CLIENT_ID);
            LocalDateTime appointmentBeginingTime = resultSet.getTimestamp(COLUMN_LABEL_BEGINING_TIME).toLocalDateTime();
            LocalDateTime appointmentEndingTime = resultSet.getTimestamp(COLUMN_LABEL_ENDING_TIME).toLocalDateTime();
            LocalDateTime appointmentOrderingTime = resultSet.getTimestamp(COLUMN_LABEL_ORDERING_TIME).toLocalDateTime();
            int appointmentStatus = resultSet.getInt(COLUMN_LABEL_STATUS_ID);
            Appointment anotherAppointmentFromDB = new Appointment(appointmentId, appointmentType, appointmentMasterId,
                    appointmetnClientId, appointmentBeginingTime, appointmentEndingTime, appointmentOrderingTime, appointmentStatus);
            appointments.add(anotherAppointmentFromDB);
        }
    }
}
