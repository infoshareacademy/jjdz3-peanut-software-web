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

    public Survey() {}
    public Survey(long id) {
        this.id =id;
    }

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

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 40, message = "Name needs to be at least 3 characters long but not longer than 40")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Surname cannot be null")
    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3, max = 60, message = "Surname needs to be at least 3 characters long but not longer than 60")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotNull(message = "Pesel cannot be null")
    @NotBlank(message = "Pesel cannot be blank")
    @Pesel
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @NotBlank(message = "Specialization missing")
    public String getPreferedSpecialization() {
        return preferedSpecialization;
    }

    public void setPreferedSpecialization(String preferedSpecialization) {
        this.preferedSpecialization = preferedSpecialization;
    }

    @NotBlank(message = "Prefered day missing")
    public String getPreferedDay() {
        return preferedDay;
    }

    public void setPreferedDay(String preferedDay) {
        this.preferedDay = preferedDay;
    }

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email has incorrect format")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Choose your sex")
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
