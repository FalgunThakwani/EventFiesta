package group11.EventFiesta.account.login.user;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler {
    @Override
    public LoginState execute(Account user, HttpServletRequest request) throws Exception {
        HttpServletRequest req = request;
        HttpSession session = req.getSession(false);
        System.out.println("Before session: "+session);
        if(session == null)
        {
            session = req.getSession(true);
            session.setAttribute("isUser", true);
            System.out.println(user.getAccountId());
            session.setAttribute("accountId", user.getAccountId());
            session.setAttribute("email", user.getEmail());
            System.out.println("Session Created! "+session.getAttribute("accountId"));
        }
        session.setAttribute("userId", user.getAccountId());
        System.out.println("After Session.");
        return new LoginSuccess();

    }

}
