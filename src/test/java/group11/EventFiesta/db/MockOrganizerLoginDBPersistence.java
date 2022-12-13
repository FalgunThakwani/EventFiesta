package group11.EventFiesta.db;

import java.util.*;

public class MockOrganizerLoginDBPersistence implements IDBPersistence{

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        List<Map<String, Object>> mock = new ArrayList<>();
        if (storedProcedure.equals("getFromDBUsingWhere")) {
            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerInfo", "organizer_id", "email", "sreedevi.rw@gmail.com"}))) {
                Map map = new HashMap();
                map.put("organizer_id", 1);
                mock.add(map);
            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerSensitive", "encrypted_password, private_key", "organizer_id", 1}))) {
                Map map = new HashMap();
                map.put("encrypted_password", "[58, 81, 61, -51, -113, 119, 8, -1, 37, 13, -100, -126, 40, -115, -3, 14]");
                map.put("private_key", "[-18, -98, -58, 13, 74, 6, 108, 0, -40, -36, -74, 59, 75, -51, -108, 121]");
                mock.add(map);
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
