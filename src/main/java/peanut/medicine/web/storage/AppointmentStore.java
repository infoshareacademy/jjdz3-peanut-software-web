package peanut.medicine.web.storage;

import peanut.medicine.web.Appointment;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Default
public class AppointmentStore {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Appointment appointment) {
        em.persist(appointment);
    }

    public List<Appointment> getFinalAppointments()
    {
        return em
                .createQuery("select a from Appointment a where a.agreed = true", Appointment.class)
                .getResultList();
    }

    public Appointment agreeToAppointment(long id)
    {
        Appointment appointment = em.find(Appointment.class, id);
        appointment.setAgreed(true);
        return appointment;
    }
}
