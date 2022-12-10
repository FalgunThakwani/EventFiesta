package group11.EventFiesta.user;

public class InvalidAccount extends LoginState{
    @Override
    public void setLoginStatus() {
        loginStatus = "INVALID ACCOUNT";
    }

    @Override
    public void setNextHtml() {
        nextHtml = "UserLogin";

    }
}
