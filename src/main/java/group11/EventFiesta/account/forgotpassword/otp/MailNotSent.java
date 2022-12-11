package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class MailNotSent extends AccountState {
    MailNotSent(Account account) {
        super(account);
    }
    @Override
    public void setStatus() {
        setStatus("Mail could not be sent due to an error");
    }

    @Override
    public void setNextPage() {
        setNextPage("ForgotPassword");
    }

}
