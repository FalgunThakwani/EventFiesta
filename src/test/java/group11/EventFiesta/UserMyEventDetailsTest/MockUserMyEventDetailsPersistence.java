package group11.EventFiesta.UserMyEventDetailsTest;

import group11.EventFiesta.DBConnection.IDBPersistence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MockUserMyEventDetailsPersistence implements IDBPersistence {
    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {

        List<Map<String, Object>> list = new LinkedList<>();
        HashMap<String, Object> map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 1);
        map.put("service_type", "decoration");
        map.put("organizer_id", 1);
        map.put("first_name", "Ruchika");
        map.put("last_name", "Nagpal");
        map.put("email", "ruchika@gmail.com");

        list.add(map);

        map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 2);
        map.put("service_type", "catering");
        map.put("organizer_id", 2);
        map.put("first_name", "Deep");
        map.put("last_name", "Naik");
        map.put("email", "deep@gmail.com");

        list.add(map);

        map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 3);
        map.put("service_type", "hall");
        map.put("organizer_id", 1);
        map.put("first_name", "Ruchika");
        map.put("last_name", "Nagpal");
        map.put("email", "ruchika@gmail.com");

        list.add(map);

        return list;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception
    {
        List<Map<String, Object>> list = new LinkedList<>();
        HashMap<String, Object> map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 1);
        map.put("service_type", "decoration");
        map.put("organizer_id", 1);
        map.put("first_name", "Ruchika");
        map.put("last_name", "Nagpal");
        map.put("email", "ruchika@gmail.com");

        list.add(map);

        map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 2);
        map.put("service_type", "catering");
        map.put("organizer_id", 2);
        map.put("first_name", "Deep");
        map.put("last_name", "Naik");
        map.put("email", "deep@gmail.com");

        list.add(map);

        map = new HashMap<>();

        map.put("event_id", 2);
        map.put("type", "Wedding");
        map.put("venue", "Halifax");
        map.put("head_count", 100);
        map.put("service_id", 3);
        map.put("service_type", "hall");
        map.put("organizer_id", 1);
        map.put("first_name", "Ruchika");
        map.put("last_name", "Nagpal");
        map.put("email", "ruchika@gmail.com");

        list.add(map);

        return list;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        return null;
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
