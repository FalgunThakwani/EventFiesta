package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.List;

public class CalculateScoreForService implements ICalculateScore {

    OrganizerService service;
    IHelperForDB helperForDB;
    private Double defaultScore;
    private Double totalNumberOfCriteria;
    protected List<Double> listOfScores;

    public CalculateScoreForService(GroupComponent groupComponent) {
        service = (OrganizerService) groupComponent;
        helperForDB = new HelperForDB();
        init();
    }

    public void init() {
        defaultScore = 4.0;
        totalNumberOfCriteria = 3.0;
        listOfScores = new ArrayList<Double>();
    }

    @Override
    public Double calculateScoreForService() {

        ICalculateScore reviewScore = new ScoreByReviews(helperForDB, service.id);
        Double rs = reviewScore.calculateScoreForService();

        ICalculateScore experinceScore = new ScoreByExperince(helperForDB, service.id);
        Double es = experinceScore.calculateScoreForService();

        ICalculateScore budgetScore = new ScoreByBudget(helperForDB, service, totalNumberOfCriteria);
        Double bs = budgetScore.calculateScoreForService();

        Double totalScore = (rs + es + bs) / totalNumberOfCriteria;
        totalScore = totalScore < defaultScore ? defaultScore : totalScore;
        return totalScore;
    }
}
