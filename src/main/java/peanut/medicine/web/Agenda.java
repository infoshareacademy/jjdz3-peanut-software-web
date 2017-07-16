package peanut.medicine.web;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import org.slf4j.LoggerFactory;
import peanut.medicine.web.iCalendar.IcalendarReaderICS;
import peanut.medicine.web.survey.Survey;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * Created by bartman3000 on 16.07.17.
 */
public class Agenda {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Agenda.class);

    private IcalendarReaderICS IcalendarReader;
    private List<Doctor> doctors;
    private List<Survey> surveyResults;

    public Agenda()
    {
        this.doctors = new ArrayList<Doctor>();
        this.surveyResults = new ArrayList<Survey>();
    }

    public List<Survey> getSurveys()
    {
        return this.surveyResults;
    }
    
    public List<Doctor> getDoctors()
    {
        return this.doctors;
    }

    public List<Doctor> getDoctorsEvents()
    {
        this.IcalendarReader = new IcalendarReaderICS();
        File[] listOfDirs = this.getElementsInDir("calendars");
        for (File d : listOfDirs)
        {
            String doctorSpecialization = d.getName();
            File[] listOfFiles = this.getElementsInDir("calendars/"+doctorSpecialization);

            for (File f : listOfFiles)
            {
                String doctorIdenityString = f.getName();
                String[] doctorIdenitySplitted = doctorIdenityString.split("\\.");
                String doctorName = doctorIdenitySplitted[0];
                String doctorSurname = doctorIdenitySplitted[1];
                Doctor doc = new Doctor(doctorName,doctorSurname, doctorSpecialization);
                doc.setCalendarFile(doctorSpecialization+"/"+doctorIdenityString);

                Calendar calendar = this.IcalendarReader.readCalendar(f);
                List<Component> vevents = calendar.getComponents("VEVENT");

                for(Component event : vevents)
                {
                    String dtStart = event.getProperty("DTSTART").getValue();
                    LocalDate term = IcalendarReaderICS.getDateTimeFromICalParam(dtStart);
                    doc.addTerm(term);
                }
                this.doctors.add(doc);
            }
        }
        return this.doctors;
    }

    protected File[] getElementsInDir(String resource) throws NullPointerException
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String elementsPath = classLoader.getResource(resource).getPath();
        File elementsDir = new File(elementsPath);
        return elementsDir.listFiles();
    }

    public List<Appointment> findBestTerms (Survey surveyResult, List<Doctor> Alldoctors)
    {
        LOGGER.info("findBestTerms()");
        LOGGER.debug("findBestTerms:surveyResult:"+ surveyResult.toString());
        LOGGER.debug("findBestTerms:Alldoctors:"+Alldoctors);

        List<Appointment> appointments = new ArrayList<>();
        String specialization = surveyResult.getPreferedSpecialization();
        String preferedDay = surveyResult.getPreferedDay();
        LOGGER.debug("findBestTerms:specialization:"+specialization);

        //take only doctor with specialization
        List<Doctor> doctors = Alldoctors.stream()
                .filter(d -> d.getSpecialization().equals(specialization))
                .collect(Collectors.toList());

        LOGGER.debug("findBestTerms:Alldoctors with preffered specialization:"+doctors);
        List<LocalDate> terms = new ArrayList<>();

        //prepare list of 2 available terms for every doctor
        for(Doctor doctor : doctors)
        {
            //step1: prepare list of days for next 10 days
            LocalDate today = LocalDate.now();
            for (int i = 1; i <= 10; i++)
            {
                terms.add(today.plusDays(i));
            }

            //step2: remove days where doctor(s) already have appointment
            //step3: remove Saturday and Sundays
            Predicate<LocalDate> filterBusyDays = localDate -> !doctor.getTerms().contains(localDate);
            Predicate<LocalDate> filterWeekendDays = localDate -> localDate.getDayOfWeek() != SATURDAY && localDate.getDayOfWeek() != SUNDAY;

            List<LocalDate> selectedTerms = terms.stream()
                    .filter(filterBusyDays)
                    .filter(filterWeekendDays)
                    .collect(Collectors.toList());

            //step4: move preferable days to top of the list
            terms = this.forcePreferredDays(selectedTerms,preferedDay);

            //step 5: return 2 terms for this doctor from top of the list
            terms = terms.subList(0, 2);
            System.out.println("Odpowiedni lekarz i 2 najlepsze terminy:\n");
            System.out.println(doctor.getName()+ " " + doctor.getSurname()+":");
            System.out.println(terms);

            for (LocalDate term : terms)
            {
                Appointment appointment = new Appointment(surveyResult, doctor, term);
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    protected static <T> void moveElementToTop(List<T> items, T input){
        int i = items.indexOf(input);
        if(i>=0){
            items.add(0, items.remove(i));
        }
    }

    protected List<LocalDate> forcePreferredDays(List<LocalDate> terms, String preferedDay)
    {
        LOGGER.info("forcePreferredDays()");
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for(LocalDate term : terms)
        {
            LOGGER.debug("forcePreferredDays:term.getDayOfWeek:"+term.getDayOfWeek().toString());
            LOGGER.debug("forcePreferredDays:term.preferedDay.toUpperCase():"+preferedDay.toUpperCase());

            if(term.getDayOfWeek().toString().equals(preferedDay.toUpperCase()))
            {
                moveElementToTop(newTerms,term);
            }
        }
        return newTerms;
    }

    public void addSurvey(Survey surveyResult)
    {
        this.surveyResults.add(surveyResult);
    }
}
