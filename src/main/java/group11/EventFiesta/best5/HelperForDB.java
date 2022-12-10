package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;

///This is riduculous it will always violate open/close principle
public class HelperForDB implements IHelperForDB {

    IDBPersistence connection = new MySQLDBPersistence();

    public HelperForDB() {
    }

    protected Integer getRatingsForService(Integer serviceId) {
        try {
            ArrayList<HashMap<String, Object>> resultSet = connection
                    .loadData("sp_getRatings", serviceId);
            return calculateRatingsForService(resultSet);
        } catch (Exception e) {
            System.out.println("Error in getting Ratings for services");
            e.printStackTrace();
            return 0;
        }
    }

    private Integer calculateRatingsForService(ArrayList<HashMap<String, Object>> resultSet) {
        Integer ratingScore = 4;
        for (int i = 0; i < resultSet.size(); i++) {
            ratingScore += (Integer) resultSet.get(i).get("rating");
        }
        if (resultSet.size() > 0) {
            ratingScore = ratingScore / resultSet.size();
        }
        return ratingScore;
    }
}
