package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler {

    HttpServletRequest request;

    CreateSessionHandler(HttpServletRequest request) {
        this.request = request;
    }
    @Override
    public IState execute(Account organizer) throws Exception {
        System.out.println("Inside CreateSessionHandler.execute()");
        HttpServletRequest req = request;
        HttpSession session = req.getSession(false);
        System.out.println("CreateSessionHandler");
        if (session == null) {
            session = req.getSession(true);
            session.setAttribute("isOrganizer", true);
            session.setAttribute("accountId", organizer.getAccountId());
            session.setAttribute("accountEmail", organizer.getEmail());
            System.out.println("Created session!");
        }
        return new LoginSuccess(organizer);
    }
}
