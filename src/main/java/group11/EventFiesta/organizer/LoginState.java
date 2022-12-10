package group11.EventFiesta.organizer;


public abstract class LoginState {

    LoginState() {
        setLoginStatus();
        setNextHtml();
    }
    String loginStatus;

    String nextPage;

    public abstract void setLoginStatus();

    public abstract void setNextHtml();

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getNextPage() {
        return nextPage;
    }
}
