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
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
            session.setAttribute("isOrganizer", true);
            System.out.println("Created session!");
        }
        return new LoginSuccess(organizer);
    }
}
