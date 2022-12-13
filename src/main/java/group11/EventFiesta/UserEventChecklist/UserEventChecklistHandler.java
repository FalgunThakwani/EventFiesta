package group11.EventFiesta.UserEventChecklist;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.*;

import java.util.*;

public class UserEventChecklistHandler
{
    IDBPersistence idbPersistence;

    public UserEventChecklistHandler(IDBPersistence dbPersistence) {
        this.idbPersistence = dbPersistence;
    }

    public List<TodoChecklist> getChecklist(int eventID) throws Exception {
        Object[] params = new Object[]{"EventCheckList","*", "event_id", eventID};
        List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        List<TodoChecklist> userEventToDoList = new ArrayList<>();
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Map<String, Object> row = data.get(i);
                TodoChecklist userEventChecklist = new TodoChecklist();
                userEventChecklist.setEventID((Integer) row.get("event_id"));
                userEventChecklist.setId((Integer) row.get("checklist_item_id"));
                userEventChecklist.setName((String) row.get("checklist_item_name"));
                userEventChecklist.setStatus((Integer) row.get("status"));
                userEventToDoList.add(userEventChecklist);
            }
        }
        return userEventToDoList;
    }

    public List<TodoChecklist> addItemToChecklist(int eventID, String checklistItemName){
        Object[] params = new Object[]{checklistItemName,eventID,new Date(), 0};
        String query = "{call sp_storeUserEventChecklistData (?,?,?,?)}";
        try {
            Integer data = idbPersistence.saveData(query, params );
            return this.getChecklist(eventID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeItem(int id)
    {
        try
        {
            int result = idbPersistence.updateData("update_event_checklist", id);
            return result > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}