package peanut.medicine.web.admin;

import peanut.medicine.web.icd9.classification.Classification;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariusz Szymanski
 */
@WebServlet(name = "ICDServlet", urlPatterns = "icd-9")
public class ICDServlet extends HttpServlet {

    @Inject @Default
    PeanutRestApiClient apiClient;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Classification classification = apiClient.getIcdClassification();
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("classification", classification);
        request.getRequestDispatcher("icd-9.jsp").forward(request, response);
    }
}
