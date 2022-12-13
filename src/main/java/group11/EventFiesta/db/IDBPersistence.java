package group11.EventFiesta.db;

import java.util.List;
import java.util.Map;

public interface IDBPersistence {

    List<Map<String, Object>> loadData(String query) throws Exception;
    List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception;
    Integer saveData(String query, Object... params) throws Exception;

    Integer updateData(String storedProcedure, Object... params) throws Exception;

    List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception;

}
