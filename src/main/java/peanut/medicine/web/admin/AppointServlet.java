package peanut.medicine.web.admin;

import peanut.medicine.doctor.Doctor;
import peanut.medicine.web.Agenda;
import peanut.medicine.web.Appointment;
import peanut.medicine.web.storage.SurveyStore;
import peanut.medicine.web.survey.Survey;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */

@WebServlet(name = "AppointServlet", urlPatterns = "/admin/appoint")
public class AppointServlet extends HttpServlet {

    @Inject @Default
    AdminStatistics statistics;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Doctor> doctors = statistics.getAllDoctors();

        SurveyStore surveyStore = new SurveyStore();
        System.out.println(surveyStore);

        System.out.println("survet:"+request.getParameter("survey"));

        long surveyId = Long.parseLong(request.getParameter("survey"));

        System.out.println("surveyId:"+surveyId);

        Survey survey = surveyStore.get(surveyId);

        Agenda agenda = new Agenda();
        List<Appointment> proposedTerms = agenda.findBestTerms(survey, doctors);

        for (Appointment proposedTerm : proposedTerms)
        {

            System.out.println(proposedTerm.toString());

        }

    }
}
