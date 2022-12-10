package group11.EventFiesta.organizer;

public class InvalidAccount extends LoginState {
    @Override
    public void setLoginStatus() {
        loginStatus = "Invalid account";
    }

    @Override
    public void setNextHtml() {
        nextPage = "OrganizerLogin";
    }
}
