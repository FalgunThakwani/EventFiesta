package group11.EventFiesta.UserEventChecklistTest;

import group11.EventFiesta.db.IDBPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserEventChecklistPersistence implements IDBPersistence
{
    List<Map<String, Object>> checklist;

    public MockUserEventChecklistPersistence()
    {
        checklist = new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception
    {
        return checklist;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("event_id", 2);
        map.put("checklist_item_id", checklist.size() + 1);
        map.put("checklist_item_name", "one");
        map.put("status", 1);

        checklist.add(map);

        return checklist;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("event_id", params[1]);
        map.put("checklist_item_id", checklist.size() + 1);
        map.put("checklist_item_name", (String) params[0]);
        map.put("status", 0);

        checklist.add(map);

        return 1;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception
    {
        int checklist_item_id = (Integer) params[0];

        for(int i = 0; i < checklist.size(); i++)
        {
            Map<String, Object> map = checklist.get(i);

            if(map.containsKey("checklist_item_id") && (Integer) map.get("checklist_item_id") == checklist_item_id)
            {
                System.out.println("matched");
                checklist.get(i).put("status", 0);
                return 1;
            }
        }

        return 0;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        return null;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        return null;
    }
}
