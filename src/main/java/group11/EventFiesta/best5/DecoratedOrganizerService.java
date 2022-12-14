package group11.EventFiesta.best5;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> resultSet = helperForDB.getOrganizerDetailsFromDB(this.organizerId);
        initializeObject(resultSet);
    }

    private void initializeObject(List<Map<String, Object>> resultSet) {
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

    public Integer getOrganizerId() {
        return organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public LocalDateTime getContact_hours_from() {
        return contact_hours_from;
    }

    public LocalDateTime getContact_hours_to() {
        return contact_hours_to;
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
                " serviceName='" + serviceName + "'" +
                ", orgranizerId='" + orgranizerId + "'" +
                ", serviceId='" + id + "'" +
                ", budget='" + budget + "'" +
                ", headCount='" + headCount + "'" +
                ", event='" + event + "'" +
                ", dateTime='" + dateTime + "'" +
                ", score='" + score + "'" +
                "}";
    }

}
