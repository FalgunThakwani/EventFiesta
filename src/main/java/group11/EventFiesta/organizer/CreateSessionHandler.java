package group11.EventFiesta.organizer;

import group11.EventFiesta.model.Organizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSessionHandler extends LoginHandler {

    @Override
    public boolean execute(Organizer organizer, HttpServletRequest request) throws Exception {
        System.out.println("Inside CreateSessionHandler.execute()");
        HttpServletRequest req = request;
        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
            session.setAttribute("isOrganizer", true);
            System.out.println("Created session!");
//            LoginState state = new
        }
        return true;
    }
}
