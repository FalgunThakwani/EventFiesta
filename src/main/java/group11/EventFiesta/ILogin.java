package group11.EventFiesta;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public interface ILogin {

    Object login(Account account, HttpServletRequest request);

    Boolean logout(HttpServletRequest request);

    //validate the security question and answer and give option to reset the password after validation
     Boolean resetPassword(Account account);

     void forgotPassword(Account account);

}
