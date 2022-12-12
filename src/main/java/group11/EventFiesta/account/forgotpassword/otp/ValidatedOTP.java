package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class ValidatedOTP extends AccountState {

    public ValidatedOTP(Account account){
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("OTP validated succesfully!");
    }

    @Override
    public void setNextPage() {
        String nextPage = "userResetPassword";
        if (account.getOrg()) {
            nextPage = "organizerResetPassword";
        }
        System.out.println(nextPage);
        setNextPage(nextPage);
    }

}
