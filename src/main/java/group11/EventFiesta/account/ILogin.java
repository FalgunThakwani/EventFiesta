package group11.EventFiesta.account;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public interface ILogin {

    Object login(Account account, HttpServletRequest request);

    Boolean logout(HttpServletRequest request);

}
