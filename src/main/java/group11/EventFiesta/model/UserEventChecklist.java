package group11.EventFiesta.model;

import java.time.Instant;

public class UserEventChecklist {

    private int checklistItemID;

    private String checklistItemName;

    private int eventID;

    private Instant completionDate;

    private boolean status;

    public int getChecklistItemID() {
        return checklistItemID;
    }

    public void setChecklistItemID(int checklistItemID) {
        this.checklistItemID = checklistItemID;
    }

    public String getChecklistItemName() {
        return checklistItemName;
    }

    public void setChecklistItemName(String checklistItemName) {
        this.checklistItemName = checklistItemName;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public Instant getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Instant completionDate) {
        this.completionDate = completionDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void userEventChecklist(){}

    public void userEventChecklist(String checklistItemName)
    {
        this.checklistItemName = checklistItemName;
        this.completionDate = Instant.now();
        this.status = false;
    }


}
