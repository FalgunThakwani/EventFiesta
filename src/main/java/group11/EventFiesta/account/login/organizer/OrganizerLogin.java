package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.ILogin;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;
import javax.servlet.http.HttpServletRequest;

public class OrganizerLogin implements ILogin {

    /*@Override*/
    public IState login(Account organizer, HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            Object [] params = new Object[] {"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence, params);
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
