package peanut.medicine.web.storage;

import peanut.medicine.web.user.User;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Default
public class UserStore {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(User user) {
        em.persist(user);
    }

}
