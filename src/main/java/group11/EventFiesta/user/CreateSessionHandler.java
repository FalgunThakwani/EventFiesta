package group11.EventFiesta.user;

import group11.EventFiesta.model.Account;
import group11.EventFiesta.user.LoginSuccess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler{
    @Override
    public LoginState execute(Account user, HttpServletRequest request) throws Exception {
        HttpServletRequest req = request;
        HttpSession session = req.getSession(false);
        System.out.println("Before session: "+session);
        if(session == null)
        {
            session = req.getSession(true);
            session.setAttribute("isUser", true);
            session.setAttribute("userId", user.getAccountId());
            System.out.println("Session Created!");
        }
        session.setAttribute("userId", user.getAccountId());
        System.out.println("After Session.");
        return new LoginSuccess();

    }

}
