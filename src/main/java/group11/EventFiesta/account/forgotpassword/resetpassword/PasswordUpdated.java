package group11.EventFiesta.account.forgotpassword.resetpassword;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class PasswordUpdated extends AccountState {

    public PasswordUpdated(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("PASSWORD UPDATED SUCCESSFULLY !!");
    }

    @Override
    public void setNextPage() {

    }
}
