package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateScoreForService implements ICalculateScore {

    OrganizerService service;
    IHelperForDB helperForDB = new HelperForDB();
    private Double defaultScore; // Out of 10
    private Double totalNumberOfCriteria;
    protected List<Double> listOfScores;

    public CalculateScoreForService(GroupComponent groupComponent) {
        service = (OrganizerService) groupComponent;
        init();
    }

    public void init() {
        defaultScore = 4.0;
        totalNumberOfCriteria = 3.0;
        listOfScores = new ArrayList<Double>();
    }

    public Double calculateRatingsForService() {
        Double totalScore = defaultScore;
        Double reviewScore = ratingsByReviews();
        Double experinceScore = ratingsByExperince();
        Double budgetScore = ratingsByBudget();
        totalScore = (reviewScore + experinceScore + budgetScore) / totalNumberOfCriteria;
        return totalScore;
    }

    public Double ratingsByReviews() {
        List<Map<String, Object>> resultSet = helperForDB.getRatingsForService(service.serviceId);
        Double total = defaultScore;
        for (int i = 0; i < resultSet.size(); i++) {
            total += (Integer) resultSet.get(i).get("rating");
        }
        if (resultSet.size() > 0) {
            total = total / (resultSet.size() * 10);
        }
        return total;
    }

    public Double ratingsByExperince() {
        List<Map<String, Object>> resultSet = helperForDB.getServiceHistory(service.serviceId);
        Double total = defaultScore;
        Double x = 0.0;
        Double y = 0.0;
        Double z = 1.0;
        Double num = 0.0;
        Double ratio = getTotalEventsRatio();
        Double scale = 10.0;
        for (int i = 0; i < resultSet.size(); i++) {
            num += (Long) resultSet.get(i).get("count(service_id)");
        }
        x = ((num / ratio) * scale) + defaultScore;
        y = ((x + z) - x);
        total = y / x;
        return total;
    }

    public Double ratingsByBudget() {
        Double total = defaultScore;
        List<Map<String, Object>> resultSet = helperForDB.getBudgetForService(service.serviceId);
        Double budget = service.budget;
        Double twentyPercentOfBudget = ((budget * 20) / 100);
        Double budget_l = budget - twentyPercentOfBudget;
        Double budget_u = budget + twentyPercentOfBudget;
        Double servicePrice = 0.0;
        Double scalingFactor = 10.0;
        for (int i = 0; i < resultSet.size(); i++) {
            servicePrice += (float) resultSet.get(i).get("price");
        }
        servicePrice *= service.headCount;
        double difference_l = budget_l - servicePrice;
        double difference_h = budget_u - servicePrice;
        if (difference_h < difference_l) {
            total = (difference_l - budget) / scalingFactor;
        } else {
            total = (difference_h - budget) / scalingFactor;
        }
        return total;
    }

    private Double getTotalEventsRatio() {
        Double ratio = 0.0;
        Double totalEvents = 0.0;
        List<Map<String, Object>> resultSet = helperForDB.getTotalEventsRatio();
        for (int i = 0; i < resultSet.size(); i++) {
            totalEvents += (Long) resultSet.get(i).get("Count(event_id)");
        }
        ratio = ((ratio + 1.0) * 100) - (totalEvents);
        return ratio;
    }

}
