package group11.EventFiesta.best5;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockDetailsFromDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ScoreByBudgetTest {

    IDBPersistence helperForDB = new MockDetailsFromDB();

    @Test
    public void calculateScoreForService() throws Exception {
        Double budget = 1000.0;
        Double numberOfServices=2.0;
        Double total = 4.0;
        Double headCount= 25.0;
        List<Map<String, Object>> resultSet = helperForDB.loadData("sp_getBudgetForService","1");
        Double twentyPercentOfBudget = ((budget * 20) / 100);
        Double budgetByService = budget / numberOfServices;
        Double budget_l = budgetByService - twentyPercentOfBudget;
        Double budget_u = budgetByService + twentyPercentOfBudget;
        Double servicePrice = 0.0;
        Double scalingFactor = 7.0;
        for (int i = 0; i < resultSet.size(); i++) {
            servicePrice += (float) resultSet.get(i).get("price");
        }
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
        Assertions.assertEquals(total,28.571428571428573);
    }
}
