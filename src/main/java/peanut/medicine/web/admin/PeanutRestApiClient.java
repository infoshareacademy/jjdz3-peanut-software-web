package peanut.medicine.web.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import peanut.medicine.web.survey.Survey;

import javax.enterprise.inject.Default;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

/**
 * Created by Mariusz Szymanski on 2017-07-24
 */
@Default
public class PeanutRestApiClient {

    private static final String PEANUT_API_SERVICE_URL = "http://localhost:8080/peanut-api/reports/";

    List<Survey> getAllSurveys() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("survey"));
        final Response response = target.request().get();
        int responseStatus = response.getStatus();
        List<Survey> surveys = new ArrayList<>();
        if (responseStatus == 200) {
            final Survey[] surveysTable = response.readEntity(Survey[].class);
            surveys.addAll(Arrays.asList(surveysTable));
        }
        response.close();
        return surveys;
    }

    Map getPreferredSpecializations() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("specialization"));
        final Response response = target.request().get();
        int responseStatus = response.getStatus();
        Map preferredSpecializations = new HashMap();
        if (responseStatus == 200) {
            preferredSpecializations = response.readEntity(Map.class);
        }
        response.close();
        return preferredSpecializations;
    }

    List<UserActivity> getAllUsersActivity() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("usersActivity"));
        final Response response = target.request().get();
        int responseStatus = response.getStatus();
        List<UserActivity> usersActivities = new ArrayList<>();
        if (responseStatus == 200) {
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule());
            try {
                final UserActivity[] activitiesTable = mapper.readValue(json, UserActivity[].class);
                usersActivities.addAll(Arrays.asList(activitiesTable));
                Collections.reverse(usersActivities);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        }
        return usersActivities;
    }

    int getReportsModuleConnectionStatus() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat("usersActivity"));
        final Response response = target.request().get();
        return response.getStatus();
    }
}
