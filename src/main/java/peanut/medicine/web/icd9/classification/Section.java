package peanut.medicine.web.icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Section {

    private String code;
    private String title;
    private List<Subsection> subsections;

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<Subsection> getSubsections() {
        return subsections;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubsections(List<Subsection> subsections) {
        this.subsections = subsections;
    }
}
