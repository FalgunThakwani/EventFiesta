package group11.EventFiesta.account.login.organizer;


import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

public abstract class LoginState implements IState {

    public Account account;

    LoginState(Account account) {
        this.account = account;
        setStatus();
        setNextPage();
    }
    String status;

    String nextPage;

    public abstract void setStatus();

    public abstract void setNextPage();

    public String getStatus() {
        return status;
    }

    public String getNextPage() {
        return nextPage;
    }
}
