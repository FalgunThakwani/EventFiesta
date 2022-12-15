package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public class OTPExpired  extends AccountState {

    public OTPExpired(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        setStatus("OTP expired!");
    }

    @Override
    public void setNextPage() {
        String nextPage = "redirect:/UserForgotPasswordOTP";
        if (account.getIsOrganizer()) {
            nextPage = "redirect:/OrganizerForgotPasswordOTP";
        }
        setNextPage(nextPage);
    }
}
