package group11.EventFiesta.organizer;

import group11.EventFiesta.model.Account;

public interface IForgotPassword {

    LoginState validate(Account account);
}
