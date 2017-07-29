package peanut.medicine.web.icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Classification {

    private List<Section> sections;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
