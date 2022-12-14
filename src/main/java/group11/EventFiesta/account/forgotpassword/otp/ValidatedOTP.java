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
        String nextPage = "redirect:/userResetPassword?email=" + account.getEmail();
        if (account.getIsOrganizer()) {
            nextPage = "redirect:/organizerResetPassword?email=" + account.getEmail();
        }
        setNextPage(nextPage);
    }

}
