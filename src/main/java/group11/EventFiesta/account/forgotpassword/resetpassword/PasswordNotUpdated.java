package group11.EventFiesta.account.forgotpassword.resetpassword;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class PasswordNotUpdated extends AccountState {

    public PasswordNotUpdated(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("PASSWORD NOT UPDATED SUCCESSFULLY !!");
    }

    @Override
    public void setNextPage() {
    }
}
