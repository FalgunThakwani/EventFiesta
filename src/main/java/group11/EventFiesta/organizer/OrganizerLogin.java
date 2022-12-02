package group11.EventFiesta.organizer;

import group11.EventFiesta.model.Organizer;
import javax.servlet.http.HttpServletRequest;

public class OrganizerLogin /*implements ILogin*/ {

    /*@Override*/
    public Boolean login(Organizer organizer, HttpServletRequest request) {
        try {
            ILoginHandler accounCheckHandler = new AccountCheckHandler();
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler();
            ILoginHandler createSessionHandler = new CreateSessionHandler();
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            return accounCheckHandler.execute(organizer, request);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Boolean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return true;
    }

    public Boolean resetPassword(String emailId, String newPassword) {
        return null;
    }
}
