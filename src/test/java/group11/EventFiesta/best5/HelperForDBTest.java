package group11.EventFiesta.best5;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockDetailsFromDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class HelperForDBTest {
    IDBPersistence mock = new MockDetailsFromDB();

    @Test
    public void getRatingsForService() throws Exception {
        List<Map<String, Object>> resultSet= mock.loadData("sp_getRatingsForService","2");
        if(resultSet.size()>0) {
            Assertions.assertEquals("5", resultSet.get(0).get("rating"));
        }
    }

    @Test
    public void getBudgetForService() throws Exception {
        List<Map<String, Object>> resultSet= mock.loadData("sp_getBudgetForService","2");
        if(resultSet.size()>0) {
            Assertions.assertEquals("15", resultSet.get(0).get("price"));
        }
    }

    @Test
    public void getTotalEventsRatio() throws Exception {
        List<Map<String, Object>> resultSet= mock.loadData("sp_getTotalEventsRatio","2");
        if(resultSet.size()>0) {
            Assertions.assertEquals("4", resultSet.get(0).get("Count(event_id)"));
        }
    }
}
