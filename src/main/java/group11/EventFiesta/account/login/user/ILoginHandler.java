package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;


public interface ILoginHandler {
    void setNextHandler(ILoginHandler handler);
    IState execute(Account account) throws Exception;
}
