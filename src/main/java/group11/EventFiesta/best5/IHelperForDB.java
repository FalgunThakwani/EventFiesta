package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;

import group11.EventFiesta.model.UserEventQuestionnaire;

public interface IHelperForDB {
    public ArrayList<HashMap<String, Object>> getOrganizersFromDB(UserEventQuestionnaire userEventQuestionnaire);

    public ArrayList<HashMap<String, Object>> getRatingsForService(Integer serviceId);

    public ArrayList<HashMap<String, Object>> getOrganizerDetailsFromDB(Integer organizerID);

    public ArrayList<HashMap<String, Object>> getServiceHistory(Integer serviceID);

    public ArrayList<HashMap<String, Object>> getTotalEventsRatio();

    public ArrayList<HashMap<String, Object>> getBudgetForService(Integer serviceId);
}
