package group11.EventFiesta.model;

public class UserEventQuestionnaire {

    private String event;
    private String province;
    private String city;
    private String dateTime;
    private String service;
    private int budget;
    private int guestCount;
    private String eventArea;

    public UserEventQuestionnaire(){}

    public UserEventQuestionnaire(String event, String province, String city, String dateTime, String service, int budget, int guestCount, String eventArea) {
        this.event = event;
        this.province = province;
        this.city = city;
        this.dateTime = dateTime;
        this.service = service;
        this.budget = budget;
        this.guestCount = guestCount;
        this.eventArea = eventArea;
    }
    
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getEventArea() {
        return eventArea;
    }

    public void setEventArea(String eventArea) {
        this.eventArea = eventArea;
    }

}
