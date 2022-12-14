package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class IncorrectSecurityCredential extends AccountState {

    public IncorrectSecurityCredential(Account account){
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("Security Details not validated successfully!");
    }

    @Override
    public void setNextPage() {

    }
}
