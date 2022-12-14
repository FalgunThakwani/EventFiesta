package group11.EventFiesta.best5;

import java.util.List;
import java.util.Map;

import group11.EventFiesta.model.UserEventQuestionnaire;

public interface IHelperForDB {
    public List<Map<String, Object>> getOrganizersFromDB(UserEventQuestionnaire userEventQuestionnaire);

    public List<Map<String, Object>> getRatingsForService(Integer serviceId);

    public List<Map<String, Object>> getOrganizerDetailsFromDB(Integer organizerID);

    public List<Map<String, Object>> getServiceHistory(Integer serviceID);

    public List<Map<String, Object>> getTotalEventsRatio();

    public List<Map<String, Object>> getBudgetForService(Integer serviceId);
}
