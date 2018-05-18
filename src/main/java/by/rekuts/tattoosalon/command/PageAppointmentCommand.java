package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.AppointmentLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.Appointment;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class PageAppointmentCommand implements ActionCommand {
    private final int PLANNING_PERIOD = 14;
    private final int HALF_AN_HOURS_IN_WORKING_DAY = 22;
    private final int SATURDAY_ORDINAL = 5;
    private final int SUNDAY_ORDINAL = 6;

    @Override
    public String execute(HttpServletRequest request) {

        LocalDate today = LocalDate.now();
        ArrayList<LocalDate> twoWeeksSinceToday = new ArrayList<>();
        ArrayList<String> daysOfWeekSinceToday = new ArrayList<>();
        for (int i = 0; i < PLANNING_PERIOD; i++) {
            LocalDate day = today.plus(i, ChronoUnit.DAYS);
            twoWeeksSinceToday.add(day);
            daysOfWeekSinceToday.add(day.getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.ENGLISH));
        }

        LocalTime beginOfWork = LocalTime.of(9, 00);
        ArrayList<LocalTime> timeOfDay = new ArrayList<>();
        for (int i = 0; i < HALF_AN_HOURS_IN_WORKING_DAY; i++) {
            LocalTime time = beginOfWork.plusMinutes(30 * i);
            timeOfDay.add(time);
        }

        /** If on appointment.jsp page master has been chosen, his id will be transfered by 'master-id' parameter.
         *  Then this id will be transfered back to appointment.jsp like a 'masterId' attribute.
         *  When we go to appointment.jsp from other pages - 'masterId' == null. */

        String masterId = request.getParameter("master-id");
        request.setAttribute("masterId", masterId);

        /**Validation. Client may not order the appointment less than an hour before meeting.*/

        LocalTime nowPlusAnHour = LocalTime.now().plusHours(1);
        int pastHoursValidateIndex = 0;
        for(int i = 0; i < timeOfDay.size(); i++ ){
            if (nowPlusAnHour.isAfter(timeOfDay.get(i))){
                pastHoursValidateIndex = i + 1;
            } else {
                break;
            }
        }
        request.setAttribute("pastHoursValidateIndex", pastHoursValidateIndex);

        /** If there are appointed meetings, user can't make appoint at this time.
         * 'appointedMeetingsTimeIndex' and 'appointedMeetingsDateIndex' pass the indexes of registered appointments to appointment.jsp */

        if (masterId != null) {
            ArrayList<Appointment> appointedMeetings = AppointmentLogic.selectAppointmentsByMasterIdForNext14Days(Integer.parseInt(masterId));
            ArrayList<Integer> appointedMeetingsTimeIndex = new ArrayList<>();
            ArrayList<Integer> appointedMeetingsDateIndex = new ArrayList<>();
            for (int i = 0; i < appointedMeetings.size(); i++) {
                int timeIndex = 0;
                int dateIndex = 0;
                for (int timeIndexCount = 0; timeIndexCount < timeOfDay.size(); timeIndexCount++) {
                    if (appointedMeetings.get(i).getBeginOfAppointment().toLocalTime().isAfter(timeOfDay.get(timeIndexCount))) {
                        timeIndex = timeIndexCount + 1;
                    } else {
                        break;
                    }
                }
                for (int dateIndexCount = 0; dateIndexCount < twoWeeksSinceToday.size(); dateIndexCount++) {
                    if (appointedMeetings.get(i).getBeginOfAppointment().toLocalDate().isAfter(twoWeeksSinceToday.get(dateIndexCount))) {
                        dateIndex = dateIndexCount + 1;
                    } else {
                        break;
                    }
                }
                appointedMeetingsTimeIndex.add(timeIndex);
                appointedMeetingsDateIndex.add(dateIndex);
            }
            request.setAttribute("appointedMeetingsTimeIndex", appointedMeetingsTimeIndex);
            request.setAttribute("appointedMeetingsDateIndex", appointedMeetingsDateIndex);
        }

        /** 'holidayDateIndex' pass the indexes of holidays to appointment.jsp. This days salon doesn't work. */

        ArrayList<Integer> holidayDateIndex = new ArrayList<>();
        for (int i = 0; i < twoWeeksSinceToday.size(); i++) {
            if (twoWeeksSinceToday.get(i).getDayOfWeek().ordinal() == SATURDAY_ORDINAL ||
                    twoWeeksSinceToday.get(i).getDayOfWeek().ordinal() == SUNDAY_ORDINAL) {
                holidayDateIndex.add(i);
            }
        }
        request.setAttribute("holidayDateIndex", holidayDateIndex);

        /** 'masters' - list of masters for choosing at page. */

        ArrayList<String> masterNames = UserLogic.selectMasters();
        ArrayList<SalonUser> masters = new ArrayList<>();
        for (int i = 0; i < masterNames.size(); i++) {
            SalonUser master = UserLogic.loadPersonalData(masterNames.get(i));
            masters.add(master);
        }
        request.setAttribute("masters", masters);

        request.setAttribute("twoWeeksSinceToday", twoWeeksSinceToday);
        request.setAttribute("daysOfWeekSinceToday", daysOfWeekSinceToday);
        request.setAttribute("timeOfDay", timeOfDay);

        return ConfigurationManager.getProperty("path.page.main");
    }
}
