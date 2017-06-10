package peanut.medicine.web.admin;

import peanut.medicine.web.survey.Survey;

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

//    void getNumberPatientsForSpec() {
//        ???
//        em.createQuery("select new peanut.medicine.web.stats.SurveyStatistics(s.preferedSpecialization, (select count(s) from Survey s GROUP BY  s.preferedSpecialization)) from Survey s",Survey.class).getResultList();
//    }

}
