package group11.EventFiesta.model;

import java.util.List;

public class UserEvent {

    private int eventID;
    private String eventType;
    private String eventVenue;
    private int guestCount;
    private List<UserService> service;

    public UserEvent() {
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public List<UserService> getService() {
        return service;
    }

    public void setService(List<UserService> service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "eventID=" + eventID +
                ", eventType='" + eventType + '\'' +
                ", eventVenue='" + eventVenue + '\'' +
                ", guestCount=" + guestCount +
                ", service=" + service +
                '}';
    }
}