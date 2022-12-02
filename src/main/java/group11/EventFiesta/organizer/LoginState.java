package group11.EventFiesta.organizer;

import group11.EventFiesta.ILogin;

public abstract class LoginState {

    ILogin login;

    LoginState(ILogin login) {
        this.login = login;
    }
    String loginStatus;

    String nextHtml;

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getNextHtml() {
        return nextHtml;
    }
}
