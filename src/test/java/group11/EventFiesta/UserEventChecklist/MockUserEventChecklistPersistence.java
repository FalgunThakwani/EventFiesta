package group11.EventFiesta.UserEventChecklist;

import group11.EventFiesta.DBConnection.IDBPersistence;

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
        HashMap<String, Object> map = new HashMap<>();

        map.put("checklist_item_name", "one");
        map.put("status", 1);
        map.put("checklist_item_id", 1);

        checklist.add(map);

        return checklist;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("checklist_item_name", "one");
        map.put("status", 1);
        map.put("checklist_item_id", 1);

        checklist.add(map);

        return checklist;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception
    {
        HashMap<String, Object> map = new HashMap<>();

        map.put("checklist_item_name", "two");
        map.put("status", 1);
        map.put("checklist_item_id", 2);

        checklist.add(map);

        return 1;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception
    {
        checklist.get(0).put("status", 0);
        return 1;
    }
}
