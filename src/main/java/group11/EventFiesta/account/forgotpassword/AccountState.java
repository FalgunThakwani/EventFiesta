package group11.EventFiesta.account.forgotpassword;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

public abstract class AccountState implements IState {

    public Account account;
    public AccountState(Account account) {
        this.account = account;
        setStatus();
        setNextPage();
    }

    String status;

    String nextPage;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
    public abstract void setStatus();

    public abstract void setNextPage();

    public String getStatus() {
        return status;
    }

    public String getNextPage() {
        return nextPage;
    }
}
