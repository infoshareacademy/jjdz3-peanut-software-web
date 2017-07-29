package peanut.medicine.web.icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Subsection {

    private String code;
    private String title;
    private List<MainCategory> mainCategories;

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<MainCategory> getMainCategories() {
        return mainCategories;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMainCategories(List<MainCategory> mainCategories) {
        this.mainCategories = mainCategories;
    }
}
