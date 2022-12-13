package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class IncorrectOTP extends AccountState {

    public IncorrectOTP(Account account) {
        super(account);
    }

    @Override
    public void setNextPage() {
        String nextPage = "UserEnterOTP";
        if (account.getOrg()) {
            nextPage = "OrganizerEnterOTP";
        }
        setNextPage(nextPage);
    }
    @Override
    public void setStatus() {
        setStatus("Incorrect OTP! Please try again..");
    }

}

