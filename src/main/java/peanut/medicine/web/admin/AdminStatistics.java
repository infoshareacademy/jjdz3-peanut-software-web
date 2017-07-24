package peanut.medicine.web.admin;

import org.apache.logging.log4j.Logger;
import peanut.medicine.doctor.Doctor;
import peanut.medicine.web.survey.Survey;
import peanut.medicine.web.user.User;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */
@Default
public class AdminStatistics {

    private static final Logger LOGGER = getLogger(AdminStatistics.class);

    @PersistenceContext
    private EntityManager em;

    public List<Survey> getAllSurveys() {
        List<Survey> surveys = em
                .createQuery("select distinct s from Survey s", Survey.class)
                .getResultList();
        return surveys;
    }

    public List<User> getAllUsers() {
        List<User> users = em
                .createQuery("select distinct u from User u", User.class)
                .getResultList();
        return users;
    }

    public List<String> getAllSpecializations() throws NullPointerException {
        List<String> specializations = new ArrayList<>();
        File folder = new File("src/main/resources/calendars");
        String[] listOfFiles = folder.list();
        if (listOfFiles != null) specializations = Arrays.asList(listOfFiles);
        return specializations;
    }

    public List<Doctor> getAllDoctors() throws NullPointerException {
        List<Doctor> doctors = new ArrayList<>();
        List<String> specializations = this.getAllSpecializations();
        for (String specialization : specializations) {
            String folderPath = ("src/main/resources/calendars/" + specialization);
//            String folderPath = getClass().getResource(("/calendars/" + specialization)).getPath();
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        String doctorIdentity = file.getName();
                        String[] doctorIdentitySplitted = doctorIdentity.split("\\.");
                        String doctorName = doctorIdentitySplitted[0];
                        String doctorSurname = doctorIdentitySplitted[1];
                        Doctor doc = new Doctor(doctorName, doctorSurname, specialization);
                        doctors.add(doc);
                    }
                }
            }
        }
        return doctors;
    }

    public Map<String, Long> getAdminStatistics() throws NullPointerException {
        List<Object[]> result = em
                .createQuery
                        ("SELECT s.preferedSpecialization, count(s.preferedSpecialization) as number FROM Survey s group BY s.preferedSpecialization").getResultList();
        Map<String, Long> adminStatistics = new HashMap<>();

        for (Object[] object : result) {
            String preferedSpec = (String) object[0];
            Long value = (Long) object[1];
            adminStatistics.put(preferedSpec, value);
        }

        return adminStatistics;
    }

    @Transactional
    public void setLoginActivity(User user, String sessionId) {

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        LocalDateTime dateTime = LocalDateTime.now();
        userActivity.setLoginTime(dateTime);
        userActivity.setSessionId(sessionId);
        em.persist(userActivity);
        LOGGER.debug("User: " + user.getEmail() + " logged in: " + dateTime);
    }

    @Transactional
    public void setLogoutActivity(String userEmail, String sessionId) {

        TypedQuery<UserActivity> query = em.createQuery(
                "select distinct u from UserActivity u where u.sessionId like :id", UserActivity.class);
        List<UserActivity> userActivities = query.setParameter("id", sessionId).getResultList();
        if (userActivities.size() != 0) {
            Optional.ofNullable(userActivities.get(0)).ifPresent(u -> {
                LocalDateTime dateTimeNow = LocalDateTime.now();
                em.find(UserActivity.class, u.getId()).setLogoutTime(dateTimeNow);
                LOGGER.debug("User: " + userEmail + " logged out: " + dateTimeNow);
            });
        }
    }
}
