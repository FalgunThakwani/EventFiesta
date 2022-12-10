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
        if(session == null)
        {
            session = req.getSession(true);
            session.setAttribute("isUser", true);
            session.setAttribute("email", user.getEmail());
            System.out.println("Session Created!");
        }
        return new LoginSuccess();

    }

}
