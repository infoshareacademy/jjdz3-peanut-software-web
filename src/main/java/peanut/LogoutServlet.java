package peanut;

import peanut.medicine.web.admin.AdminStatistics;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by moody on 29.05.17.
 */
@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Inject
    @Default
    AdminStatistics statistics;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");
        String sessionId = session.getId();
        statistics.setLogoutActivity(userEmail, sessionId);
        session.invalidate();

        String referer = request.getHeader("Referer");
        if(referer == null || referer.isEmpty())
        {
            referer = "/peanut";
        }

        response.sendRedirect(referer);
    }
}