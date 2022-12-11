package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.model.Account;

public class IncorrectPassword extends LoginState {

    IncorrectPassword(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        status = "Entered password is incorrect";
    }

    @Override
    public void setNextPage() {
        nextPage = "organizerLogin";
    }
}
