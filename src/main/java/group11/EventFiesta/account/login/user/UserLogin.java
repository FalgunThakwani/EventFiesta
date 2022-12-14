package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.account.ILogin;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public class UserLogin implements ILogin {
    @Override
    public IState login(Account user, HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            Object [] params = new Object[] {"UserInfo", "user_id", "email", user.getEmail()};
            ILoginHandler accountCheckHandler = new AccountCheckHandler(mySQLDBPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(request);
            accountCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            return accountCheckHandler.execute(user);
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

    public void forgotPassword(Account account) {}

    @Override
    public Boolean resetPassword(Account account) {
        return null;
    }
}
