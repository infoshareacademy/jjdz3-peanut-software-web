package peanut.medicine.web.storage;

import peanut.medicine.web.survey.Survey;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Default
public class SurveyStore {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Survey survey) {
        em.persist(survey);
    }

    public Survey get(long id)
    {
//        System.out.println(Survey.class);
//        System.out.println(id);

        Survey survey = new Survey(id);
        return em.find(Survey.class, survey.getId());

    }

}
