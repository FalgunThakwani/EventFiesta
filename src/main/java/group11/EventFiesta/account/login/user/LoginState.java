package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

public abstract class LoginState implements IState {

    public Account account;

    LoginState(Account account)
    {
        this.account = account;
        setStatus();
        setNextPage();
    }

    String status;
    String nextPage;
    @Override
    public String getStatus() {
        return status;
    }

    public abstract void setStatus();

    @Override
    public String getNextPage() {
        return nextPage;
    }

    public abstract void setNextPage();




}
