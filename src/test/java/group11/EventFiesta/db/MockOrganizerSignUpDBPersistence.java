package group11.EventFiesta.db;

import java.util.*;

public class MockOrganizerSignUpDBPersistence implements IDBPersistence{

  @Override
  public List<Map<String, Object>> loadData(String query) throws Exception {
    return null;
  }

  @Override
  public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
    List<Map<String, Object>> mock = new ArrayList<>();
    if(storedProcedure.equals("getFromDBUsingWhere")) {
      if(Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerInfo", "email", "email", "dp860359@dal.ca"}))) {
        Map<String, Object> row = new HashMap<>();
        row.put("organizer_id", 1);
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
