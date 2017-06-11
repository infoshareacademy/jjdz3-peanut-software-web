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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();

        Boolean logged;
        Optional<Object> loggedObj = Optional.ofNullable(request.getSession().getAttribute("logged"));
        if(loggedObj.isPresent())
        {
            logged = true;

        } else {
            logged = false;
        }

        request.setAttribute("logged", logged);
        System.out.println("sessioId:"+sessionId);
        System.out.println("logged:"+logged);
        request.getRequestDispatcher("index3.jsp").forward(request, response);
    }


    @Override
    protected void doPost (HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            System.out.println("User name: " + name);
            System.out.println("User email: " + email);

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            storage.add(user);

            HttpSession session = req.getSession(true);
//            session.setAttribute("userName", name);
            session.setAttribute("logged", true);
            req.setAttribute("logged", true);

            resp.sendRedirect("/peanut");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}