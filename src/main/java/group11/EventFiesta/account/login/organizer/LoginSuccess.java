package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.model.Account;

public class LoginSuccess extends LoginState {

    LoginSuccess(Account account) {
        super(account);
    }

    public void setNextPage() {
        nextPage = "organizerDetails";
    }

    public void setStatus() {
        status = "Successfully logged in";
    }
}
