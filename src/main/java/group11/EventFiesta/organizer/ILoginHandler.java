package group11.EventFiesta.organizer;


import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public interface ILoginHandler {

    void setNextHandler(ILoginHandler handler);

    LoginState execute(Account organizer) throws Exception;

}
