package peanut.medicine.web.icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class MainCategory {

    private String code;
    private String title;
    private List<DetailedCategory> detailedCategories;

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<DetailedCategory> getDetailedCategories() {
        return detailedCategories;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetailedCategories(List<DetailedCategory> detailedCategories) {
        this.detailedCategories = detailedCategories;
    }
}
