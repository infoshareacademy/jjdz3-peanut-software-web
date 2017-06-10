package peanut.medicine.web.stats;

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
 * Created by mariusz on 10.06.17
 */
@WebServlet(name = "StatisticServlet", urlPatterns = "/stats")
public class StatisticServlet extends HttpServlet {

    @Inject @Default
    Statistic statistic;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Survey> surveys = statistic.getAllSurveys();

        request.setAttribute("surveys", surveys);

        request.getRequestDispatcher("stats.jsp").forward(request,response);
    }
}
