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
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
            session.setAttribute("isOrganizer", true);
            session.setAttribute("accountId", organizer.getAccountId());
            session.setAttribute("accountEmail", organizer.getEmail());
        }
        return new LoginSuccess(organizer);
    }
}
