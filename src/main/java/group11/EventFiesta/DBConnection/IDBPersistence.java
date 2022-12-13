package group11.EventFiesta.DBConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDBPersistence {

    public List<Map<String, Object>> loadData(String query) throws Exception;
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception;
    public Integer saveData(String query, Object... params) throws Exception;

    public Integer updateData(String storedProcedure, Object... params) throws Exception;

    List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception;
}
