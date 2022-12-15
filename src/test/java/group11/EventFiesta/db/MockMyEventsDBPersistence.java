package group11.EventFiesta.db;

import java.util.*;

public class MockMyEventsDBPersistence implements IDBPersistence{

  @Override
  public List<Map<String, Object>> loadData(String query) throws Exception {
    return null;
  }

  @Override
  public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
    List<Map<String, Object>> mock = new ArrayList<>();
    if(storedProcedure.equals("sp_getUserEvents")){
      if(Arrays.toString(params).equals(Arrays.toString(new Object[]{1}))) {
        Map<String, Object> row = new HashMap<>();
        row.put("event_id", 1);
        row.put("service_id", 1);
        row.put("type", "Wedding");
        row.put("venue", "Ha;ifax");
        row.put("event_date","2022-12-12T01:45:01");
        row.put("total_cost", 1000);
        row.put("head_count", 50);
        row.put("service_type", "Decoration");
        row.put("cost", 10);
        row.put("Status", "Pending");
        mock.add(row);
      }
    }
    return mock;
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
