package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.ILogin;
import group11.EventFiesta.model.Account;
import javax.servlet.http.HttpServletRequest;

public class OrganizerLogin implements ILogin {

    /*@Override*/
    public LoginState login(Account organizer, HttpServletRequest request) {
        try {
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(request);
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            return accounCheckHandler.execute(organizer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Boolean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return true;
    }

    @Override
    public Boolean resetPassword(Account account) {
        return null;
    }

    public void forgotPassword(Account account) {
        return;
    }
}
