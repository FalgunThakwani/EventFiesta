package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.model.Account;

public interface IForgotPassword {

    IState validate(Account account);
}
