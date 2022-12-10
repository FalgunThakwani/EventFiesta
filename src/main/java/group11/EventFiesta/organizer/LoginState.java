package group11.EventFiesta.organizer;


public abstract class LoginState {

    LoginState() {
        setLoginStatus();
        setNextPage();
    }
    String loginStatus;

    String nextPage;

    public abstract void setLoginStatus();

    public abstract void setNextPage();

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getNextPage() {
        return nextPage;
    }
}
