package group11.EventFiesta.user;

public abstract class LoginState {

    LoginState()
    {
        setLoginStatus();
        setNextHtml();
    }
    String loginStatus;
    String nextHtml;

    public abstract void setLoginStatus();
    public abstract void setNextHtml();

    public String getLoginStatus()
    {
        return loginStatus;
    }

    public String getNextHtml()
    {
        return nextHtml;
    }
}
