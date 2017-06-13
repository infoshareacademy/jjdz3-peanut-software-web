package peanut;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
import java.util.Optional;

/**
 * Created by moody on 28.05.17.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Inject
    @Default
    UserStore storage;

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name;
        String email;
        String imageUrl = req.getParameter("imageUrl");

        try {
            String idToken = req.getParameter("id_token");
            try {
                GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                name = (String) payLoad.get("name");
                email = payLoad.getEmail();

            } catch (IllegalArgumentException e)
            {
                name = req.getParameter("name");
                email = req.getParameter("email");
            }

            System.out.println("User name: " + name);
            System.out.println("User email: " + email);
            System.out.println("imageUrl: " + imageUrl);

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            storage.add(user);

            HttpSession session = req.getSession(true);
            session.setAttribute("logged", true);
            session.setAttribute("name", name);
            session.setAttribute("email", email);

            System.out.println("logged:"+session.getAttribute("logged"));
            System.out.println("name:"+session.getAttribute("name"));
            System.out.println("email:"+session.getAttribute("email"));

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