package group11.EventFiesta.account.login.user;

import group11.EventFiesta.model.Account;

public class InvalidAccount extends LoginState {

    public InvalidAccount(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        status = "Invalid Account !! Please Try With Valid Account";
    }

    @Override
    public void setNextPage() {
        nextPage = "UserLogin";
    }

}
