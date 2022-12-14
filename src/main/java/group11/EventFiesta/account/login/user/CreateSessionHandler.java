package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler {

    HttpServletRequest httpServletRequest;

    CreateSessionHandler(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    @Override
    public IState execute(Account user) throws Exception {
        HttpSession session = httpServletRequest.getSession(false);
        if(session == null)
        {
            session = httpServletRequest.getSession(true);
            session.setAttribute("isUser", true);
            session.setAttribute("accountId", user.getAccountId());
            session.setAttribute("email", user.getEmail());
            System.out.println("Session Created! "+session.getAttribute("accountId"));
        }
        return new LoginSuccess(user);
    }

}
