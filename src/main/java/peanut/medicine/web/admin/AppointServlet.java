package peanut.medicine.web.admin;

import org.apache.logging.log4j.Logger;
import peanut.medicine.doctor.Doctor;
import peanut.medicine.web.Agenda;
import peanut.medicine.web.Appointment;
import peanut.medicine.web.iCalendar.IcalendarVEvent;
import peanut.medicine.web.storage.AppointmentStore;
import peanut.medicine.web.storage.SurveyStore;
import peanut.medicine.web.survey.Survey;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */

@WebServlet(name = "AppointServlet", urlPatterns = "/admin/appoint")
public class AppointServlet extends HttpServlet {

    private static final Logger LOGGER = getLogger(AppointServlet.class);

    @Inject
    SurveyStore surveyStore;

    @Inject
    AppointmentStore appointmentStore;

    @Inject
    AdminStatistics statistics;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Doctor> doctors = statistics.getAllDoctors();

        long surveyId = Long.parseLong(request.getParameter("survey"));
        Survey survey = surveyStore.find(surveyId);

        Agenda agenda = new Agenda();
        List<Appointment> proposedTerms = agenda.findBestTerms(survey, doctors);

        for (Appointment proposedTerm : proposedTerms)
        {
            appointmentStore.add(proposedTerm);
            LOGGER.debug(proposedTerm.getTerm());
        }

        response.sendRedirect("/peanut/admin#survey"+surveyId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long appointmentId = Long.parseLong(req.getParameter("appointment"));
        Appointment appointment = appointmentStore.agreeToAppointment(appointmentId);

        Doctor doctor = new Doctor(
                appointment.getDoctorName(),
                appointment.getDoctorSurname(),
                appointment.getDoctorSpecialization());
        doctor.setCalendarFile(appointment.getDoctorCalendar());
        appointment.setDoctor(doctor);
        long surveyId = appointment.getSurvey().getId();

        IcalendarVEvent icalendarVEvent = new IcalendarVEvent();

        icalendarVEvent.addVisitForDoctor(appointment);

        try {
            icalendarVEvent.generateInvitation(appointment);
        } catch (Exception e)
        {
            LOGGER.error("generateInvitation Error"+ e.getMessage() + e.getCause());
        }

        resp.sendRedirect("/peanut/admin#survey"+surveyId);
    }
}
