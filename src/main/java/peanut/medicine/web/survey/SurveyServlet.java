package peanut.medicine.web.survey;

import org.apache.logging.log4j.Logger;
import peanut.medicine.web.admin.AdminStatistics;
import peanut.medicine.web.storage.SurveyStore;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by Bartek on 2017-05-19.
 */
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet{

    @Inject
    SurveyStore storage;
    @Inject
    AdminStatistics statistics;

    private static final Logger LOGGER = getLogger(SurveyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Optional<Object> logged = Optional.ofNullable(session.getAttribute("logged"));
        if(logged.isPresent())
        {
            boolean isLogged = !logged.get().toString().isEmpty();
            if(isLogged)
            {
                LOGGER.debug("logged:"+session.getAttribute("logged"));
                LOGGER.debug("name:"+session.getAttribute("name"));
                LOGGER.debug("email:"+session.getAttribute("email"));
                LOGGER.debug("admin:"+session.getAttribute("admin"));

                req.setAttribute("name", session.getAttribute("name"));
                req.setAttribute("surname", session.getAttribute("surname"));
                req.setAttribute("email", session.getAttribute("email"));
            }
        }

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("specializations", statistics.getAllSpecializations());
        req.getRequestDispatcher("survey.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        Survey survey = makeSurveyFromParams(req);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Survey>> violations = validator.validate(survey);

        if (violations.size() > 0) {
            LOGGER.debug(violations);
            req = copyParamsToAttribs(req);
            req.setAttribute("violations", violations);
            req.setAttribute("specializations", statistics.getAllSpecializations());
            req.getRequestDispatcher("survey.jsp").forward(req, resp);

        } else {
            storage.add(survey);
            req = copyParamsToAttribs(req);
            req.getRequestDispatcher("survey-sent.jsp").forward(req, resp);
        }
    }

    private Survey makeSurveyFromParams(HttpServletRequest req)
    {
        Survey survey = new Survey();
        survey.setName(req.getParameter("name"));
        survey.setSurname(req.getParameter("surname"));
        survey.setPesel(req.getParameter("pesel"));
        survey.setSex(req.getParameter("sex"));
        survey.setEmail(req.getParameter("email"));
        survey.setPreferedSpecialization(req.getParameter("preferedSpecialization"));
        survey.setPreferedDay(req.getParameter("preferedDay"));
        return survey;
    }

    private HttpServletRequest copyParamsToAttribs(HttpServletRequest req)
    {
        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("surname", req.getParameter("surname"));
        req.setAttribute("pesel", req.getParameter("pesel"));
        req.setAttribute("sex", req.getParameter("sex"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("preferedSpecialization", req.getParameter("preferedSpecialization"));
        req.setAttribute("preferedDay", req.getParameter("preferedDay"));
        return req;
    }
}