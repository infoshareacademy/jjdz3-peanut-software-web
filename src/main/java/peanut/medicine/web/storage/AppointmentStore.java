package peanut.medicine.web.storage;

import org.slf4j.LoggerFactory;
import peanut.medicine.web.Appointment;
import peanut.medicine.web.survey.Survey;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Default
public class AppointmentStore {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AppointmentStore.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Appointment appointment) {
        em.persist(appointment);
    }

    public List<Appointment> getFinalAppointments()
    {
        return em
                .createQuery("select a from Appointment a where a.selected = 1", Appointment.class)
                .getResultList();
    }

    @Transactional
    public Appointment agreeToAppointment(long id)
    {
        Appointment appointment = em.find(Appointment.class, id);
        appointment.setSelected(1);
        Survey survey = appointment.getSurvey();

        Appointment visit2delete =
                em.createQuery("select a from Appointment a where a.survey = :survey and a.id <> :id", Appointment.class)
                .setParameter("survey", survey)
                .setParameter("id", id)
                .getSingleResult();

        System.out.println(visit2delete.toString());

        this.delete(visit2delete);
        return appointment;
    }

    @Transactional
    public void delete(Appointment appointment)
    {
        em.remove(appointment);
    }
}
