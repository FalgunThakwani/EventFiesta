package group11.EventFiesta.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Organizer implements Account {
    private String firstName;
    private String lastName;
    private String email;
    private String business;
    private String password;
    private String confirmPassword;
    private int businessNo;
    private String address;
    private String City;
    private String province;
    private String pincode;
    private String fromcontact;
    private String tocontact;
    private List<Service> service;

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public String getFromcontact() {
        return fromcontact;
    }

    public void setFromcontact(String fromcontact) {
        this.fromcontact = fromcontact;
    }

    public String getTocontact() {
        return tocontact;
    }

    public void setTocontact(String tocontact) {
        this.tocontact = tocontact;
    }


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(int businessNo) {
        this.businessNo = businessNo;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setAccountId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Integer getAccountId() {
        return organizerId;
    }

    @Override
    public boolean verifyEmailAddress() {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
