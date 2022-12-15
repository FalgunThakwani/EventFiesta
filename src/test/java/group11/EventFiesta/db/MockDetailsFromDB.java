package group11.EventFiesta.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDetailsFromDB implements IDBPersistence {

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {

        if (storedProcedure.equals("sp_getOrganizerDetails")) {
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("firstName", "falgun");
            map.put("lastName", "thakwani");
            map.put("email", "falgun@gmail.com");
            map.put("province", "Nova Scotia");
            map.put("city", "Halifax");
            map.put("contact_hours_from", "2022-01-30");
            map.put("contact_hours_to", "2022-01-30");
            map.put("pincode", "B3N3H6");
            mock.add(map);
            return mock;
        }else if( storedProcedure.equals("sp_getRatingsForService")){
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("rating","5");
            mock.add(map);
            return mock;
        }else if( storedProcedure.equals("sp_getTotalEventsRatio")){
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("Count(event_id)","4");
            mock.add(map);
            return mock;
        }else if( storedProcedure.equals("getOrganizersMatchingUserQuestionare")){
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("organizer_id","4");
            map.put("service_id","4");
            map.put("service_type","4");
            map.put("price","4");
            mock.add(map);
            return mock;
        }else if ( storedProcedure.equals("sp_getBudgetForService")){
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("price","15");
            mock.add(map);
        }
        return  new ArrayList<>();
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
