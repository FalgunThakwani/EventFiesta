package group11.EventFiesta.best5;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockDetailsFromDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ScoreByExperinceTest {

    IDBPersistence helperForDB = new MockDetailsFromDB();

    @Test
    public void calculateScoreForService() throws Exception {
        Double ratio = 0.0;
        Double totalEvents = 0.0;
        List<Map<String, Object>> resultSet = helperForDB.loadData("sp_getServiceHistory","1");
        for (int i = 0; i < resultSet.size(); i++) {
            totalEvents += (Long) resultSet.get(i).get("Count(event_id)");
        }
        ratio = ((ratio + 1.0) * 100) - (totalEvents);
        Assertions.assertEquals(ratio,100);
    }
}
