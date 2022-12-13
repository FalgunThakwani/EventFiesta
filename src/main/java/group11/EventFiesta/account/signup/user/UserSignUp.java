package group11.EventFiesta.account.signup.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.ISignup;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.User;
import group11.EventFiesta.security.EncryptPassword;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserSignUp extends ISignup {
    private IDBPersistence connection;

    public UserSignUp(IDBPersistence conPersistence) {
        this.connection = conPersistence;
    }

    @Override
    public boolean validateUser(Account user) throws Exception {
        List<Map<String, Object>> resultSet = connection.loadData("sp_checkUserExists", user.getEmail());
        if (resultSet.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void storeInfo(Account object) throws Exception {
        User user = (User) object;
        Object[] params = createParams(user);
        connection.loadData("sp_storeUserData", params);
    }

    private Object[] createParams(User user) {
        String key = EncryptPassword.getNextSalt();
        String encryptedPassword = encryptReceivedPassword(user.getPassword(), key);
        Object[] params = { user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getSecurityQuestion(), user.getSecurityAnswer(), getSqlDateTime(0), getSqlDateTime(0),
                encryptedPassword, key, getSqlDateTime(15), 0 };
        return params;
    }

    private String getSqlDateTime(int addNumberofDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(sqlDate.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.add(Calendar.DAY_OF_MONTH, addNumberofDays);
        String date = sdf.format(cal.getTime());
        return date;
    }

}
