package group11.EventFiesta.best5;

import java.util.List;
import java.util.Map;

public class ScoreByBudget implements ICalculateScore {

    private IHelperForDB helperForDB;
    private Integer serviceId;
    private Double budget;
    private Double numberOfServices;
    private Double headCount;

    public ScoreByBudget(IHelperForDB helperForDB, OrganizerService service, Double numberOfServiceByUser) {
        this.helperForDB = helperForDB;
        this.serviceId = service.id;
        this.budget = service.budget;
        this.numberOfServices = numberOfServiceByUser;
        this.headCount = service.headCount;
    }

    @Override
    public Double calculateScoreForService() {
        Double total = 4.0;
        List<Map<String, Object>> resultSet = helperForDB.getBudgetForService(serviceId);
        Double twentyPercentOfBudget = ((budget * 20) / 100);
        Double budgetByService = budget / numberOfServices;
        Double budget_l = budgetByService - twentyPercentOfBudget;
        Double budget_u = budgetByService + twentyPercentOfBudget;
        Double servicePrice = 0.0;
        Double scalingFactor = 7.0;
        for (int i = 0; i < resultSet.size(); i++) {
            servicePrice += (float) resultSet.get(i).get("price");
        }
        // servicePrice = 15.0;
        servicePrice *= headCount;
        double difference_l = budget_l - servicePrice;
        difference_l *= -1;
        double difference_h = budget_u - servicePrice;
        if (difference_h < difference_l) {
            total = (budgetByService - difference_l) / scalingFactor;
        } else {
            total = (difference_h - budgetByService) / scalingFactor;
        }
        if (total < 0) {
            total *= -1;
        }
        return total;
    }

}
