package peanut.medicine.web.admin;

import peanut.medicine.web.survey.Survey;
import peanut.medicine.web.user.User;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-06-10
 */
@Default
public class AdminStatistics {

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

//    void getNumberPatientsForSpec() {
//        ???
//        em.createQuery("select new peanut.medicine.web.stats.SurveyStatistics(s.preferedSpecialization, (select count(s) from Survey s GROUP BY  s.preferedSpecialization)) from Survey s",Survey.class).getResultList();
//    }

}
