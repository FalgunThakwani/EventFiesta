package group11.EventFiesta.best5;

import java.util.List;
import java.util.Map;

public class ScoreByReviews implements ICalculateScore {

    private IHelperForDB helperForDB;
    private Integer serviceId;

    public ScoreByReviews(IHelperForDB helperForDB, Integer serviceId) {
        this.helperForDB = helperForDB;
        this.serviceId = serviceId;
    }

    @Override
    public Double calculateScoreForService() {
        List<Map<String, Object>> resultSet = helperForDB.getRatingsForService(serviceId);
        Double total = 0.0;
        for (int i = 0; i < resultSet.size(); i++) {
            total += (Integer) resultSet.get(i).get("rating");
        }
        if (resultSet.size() > 0) {
            total = total / (resultSet.size() * 10);
        }
        if (total < 4.0) {
            total = 4.0;
        }
        return total;
    }

}
