package peanut.medicine.web.survey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import peanut.medicine.web.validation.constraints.Pesel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String sex;
    private String email;
    private String pesel;
    private String preferedSpecialization;
    private String preferedDay;
    private LocalDateTime creationTime;

    @PrePersist
    void create() {
        creationTime = LocalDateTime.now();
    }

    @NotBlank(message = "Imię nie może być puste")
    @Size(min = 3, max = 40, message = "Imię musi mieć od 3 do 40 znaków")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Nazwisko nie może być puste")
    @Size(min = 3, max = 60, message = "Nazwisko musi mieć od 3 do 60 znaków")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotBlank(message = "Pesel nie może być pusty")
    @Pesel
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPreferedSpecialization() {
        return preferedSpecialization;
    }

    public void setPreferedSpecialization(String preferedSpecialization) {
        this.preferedSpecialization = preferedSpecialization;
    }

    public String getPreferedDay() {
        return preferedDay;
    }

    public void setPreferedDay(String preferedDay) {
        this.preferedDay = preferedDay;
    }

    @NotBlank(message = "Email nie może być pusty")
    @Email(message = "Email ma zły format")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
