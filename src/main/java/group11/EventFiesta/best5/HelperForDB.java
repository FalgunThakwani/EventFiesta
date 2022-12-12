package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.UserEventQuestionnaire;

public class HelperForDB implements IHelperForDB {

    IDBPersistence connection = new MySQLDBPersistence();

    @Override
    public ArrayList<HashMap<String, Object>> getRatingsForService(Integer serviceId) {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getRatings", serviceId);
            return resultSet;
        } catch (Exception e) {
            System.out.println("Error in getting Ratings for services");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<HashMap<String, Object>> getOrganizersFromDB(UserEventQuestionnaire userEventQuestionnaire) {
        float twentyPercentOfBudget = ((userEventQuestionnaire.getBudget() * 20) / 100);
        float budget_l = userEventQuestionnaire.getBudget() - twentyPercentOfBudget;
        float budget_u = userEventQuestionnaire.getBudget() + twentyPercentOfBudget;
        Integer guestCount = userEventQuestionnaire.getGuestCount();
        if (guestCount < 0) {
            guestCount = 1;
        }
        Object[] params = { userEventQuestionnaire.getCity(), userEventQuestionnaire.getService(), budget_l, budget_u,
                guestCount };
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("getOrganizersMatchingUserQuestionare", params);
            return resultSet;
        } catch (Exception e) {
            System.out.print("Error in getting organizers from DB.");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public ArrayList<HashMap<String, Object>> getOrganizerDetailsFromDB(Integer organizerID) {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getOrganizerDetails", organizerID);
            return resultSet;
        } catch (Exception e) {
            System.out.println("Error in getting organizer details form DB");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<HashMap<String, Object>> getServiceHistory(Integer serviceID) {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getServiceHistory", serviceID);
            return resultSet;
        } catch (Exception e) {
            System.out.println("Error in getting service history form DB");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<HashMap<String, Object>> getTotalEventsRatio() {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getTotalEventsRatio", "event_id");
            return resultSet;
        } catch (Exception e) {
            System.out.println("Error in getting Total Events Ratio form DB");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<HashMap<String, Object>> getBudgetForService(Integer serviceId) {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getBudgetForService", serviceId);
            return resultSet;
        } catch (Exception e) {
            System.out.println("Error in getting budget for service form DB");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
