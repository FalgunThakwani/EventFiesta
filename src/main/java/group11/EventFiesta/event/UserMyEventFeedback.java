package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Reviews;

public class UserMyEventFeedback {

    IDBPersistence idbPersistence;
    public UserMyEventFeedback(IDBPersistence dbPersistence) {
        this.idbPersistence = dbPersistence;
    }

    public Reviews getUserMyEventServiceDetails(int event_id,int service_id, String service_type ) throws Exception {
        Reviews userReview = new Reviews();
        userReview.setEventId(event_id);
        userReview.setServiceId(service_id);
        userReview.setServiceType(service_type);
        return userReview;
    }

    public boolean submitUserMyEventFeedback(int eventId, int serviceId, float rating, String review) throws Exception {
        Object[] params = new Object[]{rating, review, serviceId, eventId};
        String storedProcedure = "sp_storeUserMyEventFeedback";

        String query = "{call " + storedProcedure + " (";
        for (Object param : params) {
            query += "?,";
        }
        StringBuffer buffer = new StringBuffer(query);
        buffer.deleteCharAt(query.length() - 1);
        query = buffer.toString();
        query += ")}";
        System.out.println(query);

        Integer data = idbPersistence.saveData(query, params);

        if(data > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
