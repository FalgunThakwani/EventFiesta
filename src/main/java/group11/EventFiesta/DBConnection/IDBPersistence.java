package group11.EventFiesta.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDBPersistence {

    public ArrayList<HashMap<String, Object>> loadData(String query) throws Exception;

    public Integer saveData(String query) throws Exception;

}
