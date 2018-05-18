package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.dao.AppointmentDAO;
import by.rekuts.tattoosalon.subject.Appointment;

import java.util.ArrayList;

public class AppointmentLogic {
    public static boolean insertAppointment(Appointment appointment) {
        return AppointmentDAO.insertAppointment(appointment);
    }

    public static ArrayList<Appointment> selectAppointmentsByMasterId(int masterId) {
        return AppointmentDAO.selectAppointmentsByMasterId(masterId);
    }

    public static ArrayList<Appointment> selectAppointmentsByMasterIdForNext14Days(int masterId) {
        return AppointmentDAO.selectAppointmentsByMasterIdForNext14Days(masterId);
    }

    public static ArrayList<Appointment> selectAppointmentsByClientIdForNext14Days(int clientId) {
        return AppointmentDAO.selectAppointmentsByClientIdForNext14Days(clientId);
    }
}
