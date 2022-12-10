package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Account;

public class GenerateOTP {

    public LoginState generateOTP(Account organizer) {
        try {
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence);
            ILoginHandler otpHandler = new OTPHandler(mySQLDBPersistence);
            accounCheckHandler.setNextHandler(otpHandler);
            return accounCheckHandler.execute(organizer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
