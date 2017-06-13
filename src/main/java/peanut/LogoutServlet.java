package peanut;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.getSession().invalidate();
        request.getSession().setAttribute("logged", false);

        System.out.println("logged:"+session.getAttribute("logged"));
        System.out.println("name:"+session.getAttribute("name"));
        System.out.println("email:"+session.getAttribute("email"));

        String referer = request.getHeader("Referer");
        if(referer == null || referer.isEmpty())
        {
            referer = "/peanut";
        }

        response.sendRedirect(referer);
    }
}