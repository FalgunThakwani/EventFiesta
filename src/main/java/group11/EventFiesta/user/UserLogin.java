package group11.EventFiesta.user;

import group11.EventFiesta.ILogin;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;

public class UserLogin implements ILogin {
    @Override
    public Object login(Account user, HttpServletRequest request) {
        return null;
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        return null;
    }

    public void forgotPassword(Account account) {

    }
    @Override
    public Boolean resetPassword(Account account) {
        return null;
    }

}
