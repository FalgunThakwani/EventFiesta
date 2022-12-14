package group11.EventFiesta.UserMyEventFeedbackTest;

import group11.EventFiesta.db.IDBPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserMyEventFeedback implements IDBPersistence {

    List<Map<String, Object>> reviews;

    public MockUserMyEventFeedback()
    {
        reviews = new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        return reviews;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        map.put("review_id", reviews.size() + 1);
        map.put("rating", 3);
        map.put("review", "average services");
        map.put("service_id", 2);
        map.put("event_id", 1);

        reviews.add(map);

        map.put("review_id", reviews.size() + 1);
        map.put("rating", 5);
        map.put("review", "excellent job");
        map.put("service_id", 3);
        map.put("event_id", 2);

        reviews.add(map);

        map.put("review_id", reviews.size() + 1);
        map.put("rating", 4);
        map.put("review", "good facilities");
        map.put("service_id", 3);
        map.put("event_id", 1);

        reviews.add(map);

        return reviews;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        map.put("review_id", reviews.size() + 1);
        map.put("rating", 1);
        map.put("review", "worst");
        map.put("service_id", 1);
        map.put("event_id", 3);

        reviews.add(map);

        return 1;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {
        return null;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        return null;
    }
}
