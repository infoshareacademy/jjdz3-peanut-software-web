package peanut;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.apache.logging.log4j.Logger;
import peanut.medicine.web.admin.AdminStatistics;
import peanut.medicine.web.storage.UserStore;
import peanut.medicine.web.user.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by moody on 28.05.17.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = getLogger(LoginServlet.class);

    @Inject
    @Default
    UserStore storage;
    @Inject @Default
    AdminStatistics statistics;

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name;
        String surname;
        String email;
        String imageUrl = req.getParameter("imageUrl");

        try {
            String idToken = req.getParameter("id_token");
            try {
                GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                name = (String) payLoad.get("given_name");
                surname = (String) payLoad.get("family_name");
                email = payLoad.getEmail();

            } catch (IllegalArgumentException e)
            {
                LOGGER.debug("IdTokenVerifierAndParser:"+e.getMessage());

                name = req.getParameter("name");
                surname = req.getParameter("surname");
                email = req.getParameter("email");
            }

            LOGGER.debug("User name: " + name);
            LOGGER.debug("User email: " + email);
            LOGGER.debug("imageUrl: " + imageUrl);

            User newUser = new User();
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setEmail(email);
            newUser.setAdmin(false);
            User user = storage.add(newUser);
            statistics.setLoginActivity(user);

            HttpSession session = req.getSession(true);
            session.setAttribute("logged", true);
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("admin", user.getAdmin());

            LOGGER.debug("logged:"+session.getAttribute("logged"));
            LOGGER.debug("name:"+session.getAttribute("name"));
            LOGGER.debug("email:"+session.getAttribute("email"));
            LOGGER.debug("admin:"+session.getAttribute("admin"));

            String referer = req.getHeader("Referer");
            if(referer == null || referer.isEmpty())
            {
                referer = "/peanut";
            }

            resp.sendRedirect(referer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}