package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class CorrectSecurityCredential extends AccountState {

    public CorrectSecurityCredential(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("Security Details validated successfully!");
    }

    @Override
    public void setNextPage() {

    }
}
