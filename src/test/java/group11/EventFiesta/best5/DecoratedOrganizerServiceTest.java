package group11.EventFiesta.best5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockDetailsFromDB;

public class DecoratedOrganizerServiceTest {

    IDBPersistence helperForDB = new MockDetailsFromDB();
    DecoratedOrganizerService obj = new DecoratedOrganizerService(new OrganizerService(), new HelperForDB());

    @Test
    public void getDetailsFromDBTest() throws Exception {
        List<Map<String, Object>> resultSet = helperForDB.loadData("sp_getOrganizerDetails", "1");
        Assertions.assertTrue(resultSet.size() > 0);
    }

}
