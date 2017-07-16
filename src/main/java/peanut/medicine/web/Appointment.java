package peanut.medicine.web;

import peanut.medicine.doctor.Doctor;
import peanut.medicine.web.survey.Survey;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by bartman3000 on 16.07.17.
 */

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Survey survey;

    @Transient
    private Doctor doctor;

    private String doctorName;
    private String doctorSurname;
    private String doctorSpecialization;
    private String doctorCalendar;
    private LocalDate term;
    private Boolean agreed;

    public Appointment(Survey survey, Doctor doctor, LocalDate term) {
        this.survey = survey;
        this.doctor = doctor;
        this.doctorName = doctor.getName();
        this.doctorSurname = doctor.getSurname();
        this.doctorSpecialization = doctor.getSpecialization();
        this.doctorCalendar = doctor.getCalendarFile();
        this.term = term;
    }

    public Appointment() {
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorCalendar() {
        return doctorCalendar;
    }

    public void setDoctorCalendar(String doctorCalendar) {
        this.doctorCalendar = doctorCalendar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }
}
