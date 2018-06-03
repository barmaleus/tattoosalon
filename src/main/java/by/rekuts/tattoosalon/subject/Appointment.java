package by.rekuts.tattoosalon.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment extends SalonEntity implements Serializable {

    private int appointmentType;
    private int masterId;
    private int clientId;
    private LocalDateTime beginOfAppointment;
    private LocalDateTime endOfAppointment;
    private LocalDateTime timeOfOrder;
    private int appointmentStatus;

    /* Some appointment types and statuses are not realized in this version of program, but it may be realized in later versions */

    public enum appointmentType {
        CONSULTATION,
        TATTOOING,
        PIERCING,
        PERMANENT_MAKEUP
    }

    public enum appointmentStatus {
        APPOINTED,
        CANCELED,
        FAILURE_TO_ATTEND      //may be a reason for adding client to black-list
    }

    public Appointment() {
    }

    public Appointment(int appointmentId, int appointmentType, int masterId, int clientId, LocalDateTime beginOfAppointment,
                       LocalDateTime endOfAppointment, LocalDateTime timeOfOrder, int appointmentStatus) {
        super(appointmentId);
        this.appointmentType = appointmentType;
        this.masterId = masterId;
        this.clientId = clientId;
        this.beginOfAppointment = beginOfAppointment;
        this.endOfAppointment = endOfAppointment;
        this.timeOfOrder = timeOfOrder;
        this.appointmentStatus = appointmentStatus;
    }

    /* constructor without appointmentId. It is necessary for registration appointment in database. Id is assigned automatically. */

    public Appointment(int appointmentType, int masterId, int clientId, LocalDateTime beginOfAppointment,
                       LocalDateTime endOfAppointment, LocalDateTime timeOfOrder, int appointmentStatus) {
        this.appointmentType = appointmentType;
        this.masterId = masterId;
        this.clientId = clientId;
        this.beginOfAppointment = beginOfAppointment;
        this.endOfAppointment = endOfAppointment;
        this.timeOfOrder = timeOfOrder;
        this.appointmentStatus = appointmentStatus;
    }

    public int getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(int appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getBeginOfAppointment() {
        return beginOfAppointment;
    }

    public void setBeginOfAppointment(LocalDateTime beginOfAppointment) {
        this.beginOfAppointment = beginOfAppointment;
    }

    public LocalDateTime getEndOfAppointment() {
        return endOfAppointment;
    }

    public void setEndOfAppointment(LocalDateTime endOfAppointment) {
        this.endOfAppointment = endOfAppointment;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public int getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentType == that.appointmentType &&
                masterId == that.masterId &&
                clientId == that.clientId &&
                appointmentStatus == that.appointmentStatus &&
                Objects.equals(beginOfAppointment, that.beginOfAppointment) &&
                Objects.equals(endOfAppointment, that.endOfAppointment) &&
                Objects.equals(timeOfOrder, that.timeOfOrder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appointmentType, masterId, clientId, beginOfAppointment, endOfAppointment, timeOfOrder, appointmentStatus);
    }
}
