package group11.EventFiesta;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public interface ILogin {

    public Object login(Account account, HttpServletRequest request);

    public Boolean logout(HttpServletRequest request);

    //validate the security question and answer and give option to reset the password after validation
    public Boolean resetPassword(Account account);


}
