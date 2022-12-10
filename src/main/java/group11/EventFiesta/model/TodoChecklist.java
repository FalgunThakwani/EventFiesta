package group11.EventFiesta.model;

public class TodoChecklist
{
    private int id;
    private String name;
    private int eventID;
    private int status;
    private boolean eventStatus;

    public void userEventChecklist(){}

    public boolean isEventStatus() {
        return this.status == 1;
    }

    public void setEventStatus(boolean eventStatus) {
        this.eventStatus = eventStatus;
        this.status = this.eventStatus ? 1 : 0;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventID=" + eventID +
                ", status=" + status +
                ", eventStatus=" + eventStatus +
                '}';
    }
}
