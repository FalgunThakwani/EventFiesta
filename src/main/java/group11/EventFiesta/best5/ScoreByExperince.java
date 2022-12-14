package group11.EventFiesta.best5;

import java.util.List;
import java.util.Map;

public class ScoreByExperince implements ICalculateScore {
    private IHelperForDB helperForDB;
    private Integer serviceId;

    public ScoreByExperince(IHelperForDB helperForDB, Integer serviceId) {
        this.helperForDB = helperForDB;
        this.serviceId = serviceId;
    }

    @Override
    public Double calculateScoreForService() {
        List<Map<String, Object>> resultSet = helperForDB.getServiceHistory(serviceId);
        Double total = 4.0;
        Double x = 0.0;
        Double y = 0.0;
        Double z = 1.0;
        Double num = 0.0;
        Double ratio = getTotalEventsRatio();
        Double scale = 10.0;
        for (int i = 0; i < resultSet.size(); i++) {
            num += (Long) resultSet.get(i).get("count(service_id)");
        }
        x = ((num / ratio) * scale) + 4.0;
        y = ((x + z) + scale);
        total = y / x;
        total = total > 10 && total < 4 ? 4.0 : total;
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
