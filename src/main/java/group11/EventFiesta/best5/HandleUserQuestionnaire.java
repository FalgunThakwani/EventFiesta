package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.model.UserEventQuestionnaire;

public class HandleUserQuestionnaire implements IHandleUserQuestionnaire {

    private UserEventQuestionnaire userEventQuestionnaire;
    private IDBPersistence dbPersistence;
    List<GroupComponent> organizersList = new ArrayList<GroupComponent>();

    public HandleUserQuestionnaire(UserEventQuestionnaire userEventQuestionnaire, IDBPersistence dPersistence) {
        this.userEventQuestionnaire = userEventQuestionnaire;
        this.dbPersistence = dPersistence;

    }

    public Map<String, List<GroupComponent>> getMapValuePairOfService() {
        Map<String, List<GroupComponent>> listOfAllOrganizers = new HashMap<String, List<GroupComponent>>();
        getOrganizersFromDB();
        String[] servicesByUser = userEventQuestionnaire.getService().split(",");

        for (int j = 0; j < servicesByUser.length; j++) {
            for (int i = 0; i < organizersList.size(); i++) {
                DecoratedOrganizerService service = (DecoratedOrganizerService) organizersList.get(i);
                List<GroupComponent> organizerOfferingSameService = new ArrayList<GroupComponent>();
                if (servicesByUser[j].equalsIgnoreCase(service.org.serviceName)) {
                    organizerOfferingSameService.add(service);
                    listOfAllOrganizers.put(servicesByUser[j], organizerOfferingSameService);
                }
            }
        }

        return listOfAllOrganizers;
    }

    public List<GroupComponent> getOrganizersFromDB() {
        float twentyPercentOfBudget = ((userEventQuestionnaire.getBudget() * 20) / 100);
        float budget_l = userEventQuestionnaire.getBudget() - twentyPercentOfBudget;
        float budget_u = userEventQuestionnaire.getBudget() + twentyPercentOfBudget;
        Object[] params = { userEventQuestionnaire.getCity(), userEventQuestionnaire.getService(), budget_l, budget_u };
        try {
            List<Map<String, Object>> resultSet = dbPersistence
                    .loadData("getOrganizersMatchingUserQuestionare", params);
            setOrganizerServiceObject(resultSet);
        } catch (Exception e) {
            System.out.print("Error in getting organizers from DB.");
            e.printStackTrace();
        }

        return organizersList;
    }

    public void setOrganizerServiceObject(List<Map<String, Object>> resultSet) {
        for (int i = 0; i < resultSet.size(); i++) {
            OrganizerService organizerService = new OrganizerService();
            organizerService.orgranizerId = Integer.parseInt(resultSet.get(i).get("organizer_id").toString());
            organizerService.serviceId = Integer.parseInt(resultSet.get(i).get("service_id").toString());
            organizerService.serviceName = resultSet.get(i).get("service_type").toString();
            GroupComponent decoratedComponent = new DecoratedOrganizerService(organizerService, dbPersistence);
            organizersList.add(decoratedComponent);
        }
    }
}
