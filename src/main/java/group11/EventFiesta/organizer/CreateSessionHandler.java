package group11.EventFiesta.organizer;

import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler {

    HttpServletRequest request;

    CreateSessionHandler(HttpServletRequest request) {
        this.request = request;
    }
    @Override
    public LoginState execute(Account organizer) throws Exception {
        System.out.println("Inside CreateSessionHandler.execute()");
        HttpServletRequest req = request;
        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
            session.setAttribute("isOrganizer", true);
            session.setAttribute("accountId", organizer.getAccountId());
            System.out.println("Created session!");
        }
        return new LoginSuccess();
    }
}
