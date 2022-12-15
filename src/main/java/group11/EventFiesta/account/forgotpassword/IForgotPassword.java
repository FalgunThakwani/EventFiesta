package group11.EventFiesta.account.forgotpassword;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

public interface IForgotPassword {

    IState validate(Account account);
}
