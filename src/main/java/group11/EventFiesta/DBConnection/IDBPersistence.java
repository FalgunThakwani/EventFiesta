package group11.EventFiesta.DBConnection;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDBPersistence {

    public ArrayList<HashMap<String, Object>> loadData(String query) throws Exception;
    public ArrayList<HashMap<String, Object>> loadData(String storedProcedure, Object... params) throws Exception;
    public Integer saveData(String query, Object... params) throws Exception;

    public Integer updateData(String storedProcedure, Object... params) throws Exception;

}
