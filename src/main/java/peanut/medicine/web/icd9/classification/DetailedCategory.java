package peanut.medicine.web.icd9.classification;

/**
 * @author Mariusz Szymanski
 */
public class DetailedCategory {

    private String code;
    private String description;
    private String notes;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
