package group11.EventFiesta.account.login.user;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public interface ILoginHandler {
    void setNextHandler(ILoginHandler handler);
    LoginState execute(Account user, HttpServletRequest request) throws Exception;
}
