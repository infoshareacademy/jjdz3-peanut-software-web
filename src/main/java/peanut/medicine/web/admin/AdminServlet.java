package peanut.medicine.web.admin;

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

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    @Inject @Default
    AdminStatistics statistics;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Survey> surveys = statistics.getAllSurveys();
        List<User> users = statistics.getAllUsers();

        request.setAttribute("surveys", surveys);
        request.setAttribute("users", users);

        request.getRequestDispatcher("admin.jsp").forward(request,response);
    }
}
