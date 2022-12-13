package group11.EventFiesta.account.forgotpassword.resetpassword;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.model.Account;


public class ResetPasswordHandler implements IForgotPassword {

    Object[] params;
    IDBPersistence idbPersistence;

    public ResetPasswordHandler(IDBPersistence idbPersistence, Object[] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }

    @Override
    public IState validate(Account account) {
        int result = 0;
        try {
            result = idbPersistence.updateData("updateDBUsingWhere", params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Updated Row Count: " + result);
        if (result == 1) {
            return new PasswordUpdated(account);
        }
        else {
            return new PasswordNotUpdated(account);
        }
    }

}

