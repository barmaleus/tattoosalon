package by.rekuts.tattoosalon.command;

import by.rekuts.tattoosalon.logic.AppointmentLogic;
import by.rekuts.tattoosalon.logic.UserLogic;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.subject.Appointment;
import by.rekuts.tattoosalon.subject.SalonUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class PageCabinetCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        SalonUser user = UserLogic.loadPersonalData(String.valueOf(request.getParameter("uname")));
        request.setAttribute("uname", user);
        request.setAttribute("publicationSum", UserLogic.selectSumAuthorsPublictions(user.getLogin()));

        ArrayList<Appointment> appointedMasterMeetings = AppointmentLogic.selectAppointmentsByMasterIdForNext14Days(user.getId());
        request.setAttribute("appointedMasterMeetings", appointedMasterMeetings);
        ArrayList<Appointment> appointedClientMeetings = AppointmentLogic.selectAppointmentsByClientIdForNext14Days(user.getId());
        request.setAttribute("appointedClientMeetings", appointedClientMeetings);

        request.setAttribute("users", UserLogic.selectAllUsers());

        return ConfigurationManager.getProperty("path.page.main");
    }
}
