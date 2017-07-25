package peanut.medicine.web.admin;

import peanut.medicine.web.survey.Survey;

import javax.enterprise.inject.Default;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Mariusz Szymanski on 2017-07-24
 */
@Default
public class PeanutRestApiClient {

    private static final String PEANUT_API_SERVICE_URL = "http://localhost:8080/peanut-api/reports/";

    List<Survey> getAllSurveys(){
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("surveys"));
        final Response response = target.request().get();
        final Survey[] surveysTable = response.readEntity(Survey[].class);
        List<Survey> surveys = new ArrayList<>();
        surveys.addAll(Arrays.asList(surveysTable));
        response.close();
        return surveys;
    }

    Map getPreferredSpecializations() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("specializations/all"));
        final Response response = target.request().get();
        final Map preferredSpecializations = response.readEntity(Map.class);
        response.close();
        return preferredSpecializations;
    }
}
