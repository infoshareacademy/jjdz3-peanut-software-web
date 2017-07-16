package peanut.medicine.web;

import peanut.medicine.web.survey.Survey;

import java.time.LocalDate;

/**
 * Created by bartman3000 on 16.07.17.
 */
public class Appointment {

    private Survey survey;
    private Doctor doctor;
    private LocalDate term;

    public Appointment(Survey survey, Doctor doctor, LocalDate term) {
        this.survey = survey;
        this.doctor = doctor;
        this.term = term;
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

}
