package peanut.medicine.web.admin;

import peanut.medicine.doctor.Doctor;
import peanut.medicine.web.Appointment;
import peanut.medicine.web.storage.AppointmentStore;
import peanut.medicine.web.survey.Survey;
import peanut.medicine.web.user.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    @Inject @Default
    AdminStatistics statistics;
    @Inject @Default
    PeanutRestApiClient apiClient;

    @Inject
    AppointmentStore appointmentStore;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Survey> surveys = statistics.getAllSurveys();
        List<User> users = statistics.getAllUsers();
        List<Doctor> doctors = statistics.getAllDoctors();
        List<Appointment> appointments = appointmentStore.getFinalAppointments();
        List<String> specializations = statistics.getAllSpecializations();
        int reportsModuleConnectionStatus = apiClient.getReportsModuleConnectionStatus();
        Map preferredSpecializations = apiClient.getPreferredSpecializations();
        List<UserActivity> usersActivities = apiClient.getAllUsersActivity();

        request.setAttribute("surveys", surveys);
        request.setAttribute("users", users);
        request.setAttribute("doctors", doctors);
        request.setAttribute("appointments", appointments);
        request.setAttribute("specializations", specializations);
        request.setAttribute("adminStatistics", preferredSpecializations);
        request.setAttribute("usersActivities", usersActivities);
        request.setAttribute("reportsModuleConnectionStatus", reportsModuleConnectionStatus);

        request.getRequestDispatcher("admin.jsp").forward(request,response);
    }
}
