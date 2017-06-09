package peanut.medicine.web.stats;


import peanut.medicine.web.survey.Survey;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mati on 09.06.17.
 */
public class Statistics {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {

        entityManagerFactory = Persistence
                .createEntityManagerFactory("PeanutDB");
        entityManager = entityManagerFactory.createEntityManager();


        Query query = entityManager.createQuery("select distinct preferedSpecialization, count(name) from peanutDB GROUP BY  preferedSpecialization", Survey.class);
        Iterator iterator = query.getResultList().iterator();
        while (iterator.hasNext()) {
            Object[] item = (Object[]) iterator.next();
            String specjalizacja = (String) item[0];
            Integer numer = (Integer) item[1];
            System.out.println(specjalizacja + "has" + numer);

        }


        entityManager.close();
        entityManagerFactory.close();
    }
}



