package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.AppointmentLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Appointment;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class OrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        boolean flag;
        String page;

        Appointment appointment;
        LocalTime beginOfWork = LocalTime.of(9, 00);
        LocalDate today = LocalDate.parse(request.getParameter("today"));
        int timeIndex = Integer.parseInt(request.getParameter("rowIndex"));
        int dateIndex = Integer.parseInt(request.getParameter("columnIndex"));
        LocalDate orderingDay = today.plus(dateIndex, ChronoUnit.DAYS);
        LocalTime orderingTime = beginOfWork.plus(30*timeIndex, ChronoUnit.MINUTES);
        LocalTime endOrderingTime = orderingTime.plus(30, ChronoUnit.MINUTES);



        int typeConsult = Appointment.appointmentType.CONSULTATION.ordinal();
        int masterId = Integer.parseInt(request.getParameter("masterId"));
        int clientId = ((SalonUser) request.getSession().getAttribute("salonUser")).getId();
        LocalDateTime orderingDateTime = LocalDateTime.of(orderingDay, orderingTime);
        LocalDateTime endOrderingDageTime = LocalDateTime.of(orderingDay, endOrderingTime);
        LocalDateTime timeOfOrder = LocalDateTime.now();
        int appointmentStatus = Appointment.appointmentStatus.APPOINTED.ordinal();
        appointment = new Appointment(typeConsult, masterId, clientId, orderingDateTime, endOrderingDageTime, timeOfOrder, appointmentStatus);
        flag = AppointmentLogic.insertAppointment(appointment);

        if(flag) {
            request.setAttribute("message", MessageManager.getProperty("message.appointment.ok"));
            page = ConfigurationManager.getProperty("path.page.confirmation");
        } else {
            request.setAttribute("message", MessageManager.getProperty("message.appointment.bad"));
            page = ConfigurationManager.getProperty("path.page.confirmation");
        }

//        if(!RegistrationLogic.checkLogin(user.getLogin()) && !RegistrationLogic.checkEmail(user.getEmail())) {
//            RegistrationLogic.insertUser(user);
//            page = ConfigurationManager.getProperty("path.page.createuser");        //new page
//        } else if (RegistrationLogic.checkLogin(user.getLogin())){
//            request.setAttribute("errorLoginExists", MessageManager.getProperty("message.errorloginexists"));
//            page = ConfigurationManager.getProperty("path.page.registration");
//        } else {
//            request.setAttribute("errorEmailExists", MessageManager.getProperty("message.erroremailexists"));
//            page = ConfigurationManager.getProperty("path.page.registration");
//        }
        return page;
    }
}
