package group11.EventFiesta.account.login.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.ILogin;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public class UserLogin implements ILogin {
    @Override
    public LoginState login(Account user, HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            ILoginHandler accountCheckHandler = new AccountCheckHandler(mySQLDBPersistence);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler();
            accountCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            return accountCheckHandler.execute(user, request);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
    @Override
    public Boolean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return true;
    }

    public void forgotPassword(Account account) {

    }
    @Override
    public Boolean resetPassword(Account account) {
        return null;
    }
}
