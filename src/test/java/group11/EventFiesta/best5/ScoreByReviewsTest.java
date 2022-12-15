package group11.EventFiesta.best5;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockDetailsFromDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ScoreByReviewsTest {
    IDBPersistence helperForDB = new MockDetailsFromDB();

    @Test
    public void calculateScoreForService() throws Exception {
        List<Map<String, Object>> resultSet = helperForDB.loadData("sp_getRatingsForService","1");
        Double total = 0.0;
        for (int i = 0; i < resultSet.size(); i++) {
            total += Integer.parseInt(resultSet.get(i).get("rating").toString());
        }
        if (resultSet.size() > 0) {
            total = total / (resultSet.size() * 10);
        }
        if (total < 4.0) {
            total = 4.0;
        }
    Assertions.assertEquals(total,4.0);
    }
}
