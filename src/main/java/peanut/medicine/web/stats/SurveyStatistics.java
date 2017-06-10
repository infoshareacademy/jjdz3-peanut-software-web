package peanut.medicine.web.stats;

public class SurveyStatistics {

    private final String preferredSpec;
    private final long surveys;

    public SurveyStatistics(String preferredSpec, long surveys) {
        this.preferredSpec = preferredSpec;
        this.surveys = surveys;
    }
}

