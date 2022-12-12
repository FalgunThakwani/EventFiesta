package group11.EventFiesta.UserEventChecklist;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.*;

import java.util.*;

public class UserEventChecklistHandler{

    IDBPersistence idbPersistence;

    public UserEventChecklistHandler() {
        this.idbPersistence = new MySQLDBPersistence();
    }

    public List<TodoChecklist> getChecklist(String eventID) throws Exception {
        Object[] params = new Object[]{"EventCheckList","*", "event_id", eventID};
        List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        List<TodoChecklist> userEventToDoList = new ArrayList<>();
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Map<String, Object> row = data.get(i);
                TodoChecklist userEventChecklist = new TodoChecklist();
                userEventChecklist.setId((Integer) row.get("checklist_item_id"));
                userEventChecklist.setName((String) row.get("checklist_item_name"));
                userEventChecklist.setStatus((Integer) row.get("status"));
                userEventToDoList.add(userEventChecklist);
            }
        }
        return userEventToDoList;
    }

    public List<TodoChecklist> addItemToChecklist(String eventID, String checklistItemName){
        //int checklistItemID = new Random().nextInt(1000000);
        Object[] params = new Object[]{checklistItemName,eventID,new Date(), 0};
        try {
            List<Map<String, Object>> data = idbPersistence.loadData("sp_storeUserEventChecklistData", params);
            return this.getChecklist("123");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeItem(int id)
    {
        try
        {
            int result = idbPersistence.updateData("update_event_checklist", id);
            System.out.println("Remove Item Count: " + result);
            return result > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}