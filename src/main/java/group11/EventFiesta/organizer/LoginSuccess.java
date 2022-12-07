package group11.EventFiesta.organizer;

import group11.EventFiesta.ILogin;

public class LoginSuccess extends LoginState {

    public void setNextHtml() {
        nextHtml = "organizerDetails";
    }

    public void setLoginStatus() {
        loginStatus = "Successfully logged in";
    }
}
