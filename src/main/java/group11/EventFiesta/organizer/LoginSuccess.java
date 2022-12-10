package group11.EventFiesta.organizer;

public class LoginSuccess extends LoginState {

    public void setNextPage() {
        nextPage = "organizerDetails";
    }

    public void setLoginStatus() {
        loginStatus = "Successfully logged in";
    }
}
