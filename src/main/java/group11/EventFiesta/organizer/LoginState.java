package group11.EventFiesta.organizer;


public abstract class LoginState {

    LoginState() {
        setLoginStatus();
        setNextHtml();
    }
    String loginStatus;

    String nextHtml;

    public abstract void setLoginStatus();

    public abstract void setNextHtml();

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getNextHtml() {
        return nextHtml;
    }
}
