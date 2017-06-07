package peanut.medicine.web.survey;

import org.apache.logging.log4j.Logger;
import peanut.medicine.web.storage.SurveyStore;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.*;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by Bartek on 2017-05-19.
 */
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet{

    @Inject
    SurveyStore storage;

    private static final Logger LOGGER = getLogger(SurveyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
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

        } else {
            storage.add(survey);
        }

        req.setAttribute("violations", violations);
        req.getRequestDispatcher("survey.jsp").forward(req, resp);
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