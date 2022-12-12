package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class SentOTP extends AccountState {

    public SentOTP(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("OTP has been sent");
    }

    @Override
    public void setNextPage() {
        String nextPage = "UserEnterOTP";
        if (account.getOrg()) {
            nextPage = "OrganizerEnterOTP";
        }
        setNextPage(nextPage);
    }

}
