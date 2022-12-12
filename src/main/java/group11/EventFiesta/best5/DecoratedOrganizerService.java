package group11.EventFiesta.best5;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;

public class DecoratedOrganizerService extends BaseDecorator {

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
    private IHelperForDB helperForDB;

    public DecoratedOrganizerService(GroupComponent organizerService, IHelperForDB helperForDB) {
        super(organizerService);
        OrganizerService org = (OrganizerService) organizerService;
        this.organizerId = org.orgranizerId;
        this.helperForDB = helperForDB;
        getDetailsFromDB();
    }

    @Override
    public Double calculateScore() {
        return super.calculateScore();
    }

    private void getDetailsFromDB() {
        ArrayList<HashMap<String, Object>> resultSet = helperForDB.getOrganizerDetailsFromDB(this.organizerId);
        buildObject(resultSet);
    }

    /// Use builder pattern to do this kind of stuff please
    private void buildObject(ArrayList<HashMap<String, Object>> resultSet) {
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

    @Override
    public String toString() {
        return "{" +
                " organizerId='" + organizerId + "'" +
                ", organizerName='" + organizerName + "'" +
                ", firstName='" + firstName + "'" +
                ", lastName='" + lastName + "'" +
                ", email='" + email + "'" +
                ", phoneNumber='" + phoneNumber + "'" +
                ", province='" + province + "'" +
                ", city='" + city + "'" +
                ", pincode='" + pincode + "'" +
                ", contact_hours_from='" + contact_hours_from + "'" +
                ", contact_hours_to='" + contact_hours_to + "'" +
                ", helperForDB='" + helperForDB + "'" +
                "}";
    }

}
