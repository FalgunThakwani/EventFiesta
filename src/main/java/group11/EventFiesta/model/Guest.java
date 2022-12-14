package group11.EventFiesta.model;

public class Guest {
    private int guestId;
    private String guestName;
    private long contactNo;
    private boolean invited;
    private boolean rsvp;

    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public long getContactNo() {
        return contactNo;
    }
    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public boolean getInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public boolean getRsvp() {
        return rsvp;
    }

    public void setRsvp(boolean rsvp) {
        this.rsvp = rsvp;
    }
}
