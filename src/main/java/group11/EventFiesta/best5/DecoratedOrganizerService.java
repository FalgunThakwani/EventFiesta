package group11.EventFiesta.best5;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.EventFiesta.DBConnection.IDBPersistence;

public class DecoratedOrganizerService extends BaseDecorator {

    private IDBPersistence connection;
    protected Integer organizerId;
    protected String organizerName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected long phoneNumber;
    protected String province;
    protected String city;
    protected String pincode;
    protected LocalDateTime contact_hours_from;
    protected LocalDateTime contact_hours_to;
    protected OrganizerService org;

    public DecoratedOrganizerService(GroupComponent organizerService, IDBPersistence connection) {
        super(organizerService);
        org = (OrganizerService) organizerService;
        this.organizerId = org.orgranizerId;
        this.connection = connection;
        getDetailsFromDB();
    }

    @Override
    public Double calculateScore() {
        return super.calculateScore();
    }

    private void getDetailsFromDB() {
        try {
            List<Map<String, Object>> resultSet = connection
                    .loadData("sp_getOrganizerDetails", org.orgranizerId);
            buildObject(resultSet);
        } catch (Exception e) {
            System.out.println("Error in getting organizer details form DB");
            e.printStackTrace();
        }
    }

    /// Use builder pattern to do this kind of stuff please
    private void buildObject(List<Map<String, Object>> resultSet) {
        if (resultSet.size() > 0) {
            organizerName = resultSet.get(0).get("name").toString();
            firstName = resultSet.get(0).get("first_name").toString();
            lastName = resultSet.get(0).get("last_name").toString();
            email = resultSet.get(0).get("email").toString();
            phoneNumber = Long.parseLong(resultSet.get(0).get("phone_number").toString());
            province = resultSet.get(0).get("province").toString();
            city = resultSet.get(0).get("city").toString();
            pincode = resultSet.get(0).get("province").toString();
            contact_hours_from = (LocalDateTime) resultSet.get(0).get("contact_hours_from");
            contact_hours_to = (LocalDateTime) resultSet.get(0).get("contact_hours_to");
        }
    }

}
