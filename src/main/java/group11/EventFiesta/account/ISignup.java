package group11.EventFiesta.account;

import group11.EventFiesta.UserEventChecklist.EncryptPassword;
import group11.EventFiesta.model.Account;

public abstract class ISignup {

    /// Validate User by checking if the provided email exists in the DB.
    /// If return true then user has to use another email address.
    public abstract boolean validateUser(Account object) throws Exception;

    /// Store information in db object specific
    public abstract void storeInfo(Account object) throws Exception;

    /// This method will encrypt the recieved password
    public String encryptReceivedPassword(String password, String key) {
        String encryptedPWD = EncryptPassword.getEncryptedPwd(password, key);
        return encryptedPWD;
    }

}
