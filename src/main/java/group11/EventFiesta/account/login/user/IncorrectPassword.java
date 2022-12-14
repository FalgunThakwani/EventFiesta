package group11.EventFiesta.account.login.user;

import group11.EventFiesta.model.Account;

public class IncorrectPassword extends LoginState {

    IncorrectPassword(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        status = "Password Incorrect !! Please enter Correct Password.";
    }

    @Override
    public void setNextPage() {
        nextPage = "userLogin";
    }
}
