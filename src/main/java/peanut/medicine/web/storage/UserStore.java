package peanut.medicine.web.storage;

import peanut.medicine.web.user.User;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Singleton
@Default
public class UserStore {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User add(User newUser) {

        User user;
        try {
            TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email",User.class);
            user = query.setParameter("email", newUser.getEmail()).getSingleResult();

        } catch (NoResultException e)
        {
            em.persist(newUser);
            user = newUser;
        }
        return user;
    }

}
