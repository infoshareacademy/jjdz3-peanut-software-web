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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by Bartek on 2017-05-19.
 */
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet{

    @Inject
    @Default
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

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String pesel = req.getParameter("pesel");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String preferedSpecialization = req.getParameter("preferedSpecialization");
        String preferedDay = req.getParameter("preferedDay");

        Survey survey = new Survey();
        survey.setName(name);
        survey.setSurname(surname);
        survey.setPesel(pesel);
        survey.setSex(sex);
        survey.setEmail(email);
        survey.setPreferedSpecialization(preferedSpecialization);
        survey.setPreferedDay(preferedDay);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Survey>> violations = validator.validate(survey);

        if (violations.size() > 0) {

            LOGGER.debug(violations);
            req.setAttribute("name", name);
            req.setAttribute("surname", surname);
            req.setAttribute("pesel", pesel);
            req.setAttribute("sex", sex);
            req.setAttribute("email", email);
            req.setAttribute("preferedSpecialization", preferedSpecialization);
            req.setAttribute("preferedDay", preferedDay);
        } else {
            storage.add(survey);
        }

        req.setAttribute("violations", violations);
        req.getRequestDispatcher("survey.jsp").forward(req, resp);
    }
}
