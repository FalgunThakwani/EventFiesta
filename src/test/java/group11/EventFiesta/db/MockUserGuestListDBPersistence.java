package group11.EventFiesta.db;

import java.util.*;

public class MockUserGuestListDBPersistence implements IDBPersistence{

  @Override
  public List<Map<String, Object>> loadData(String query) throws Exception {
    return null;
  }

  @Override
  public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
    List<Map<String, Object>> mock = new ArrayList<Map<String, Object>>();
    if(storedProcedure.equals("sp_getUserEvents")){
      if(Arrays.toString(params).equals(Arrays.toString(new Object[]{1}))) {
        Map<String, Object> row = new HashMap<>();
        row.put("event_id", 1);
        row.put("user_guest_id", "1");
        row.put("user_guest_name", "Deep");
        row.put("user_guest_contact", "9876543210");
        row.put("invited", 1);
        row.put("rsvp", 1);
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
