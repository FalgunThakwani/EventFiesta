package group11.EventFiesta.ForgotPassword;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;

import java.util.ArrayList;
import java.util.HashMap;

public class SecretQuestionHandler {

    IDBPersistence idbPersistence;

    public SecretQuestionHandler() {
        idbPersistence = new MySQLDBPersistence();
    }

    public Integer validateEmail(String email) throws Exception {
        Object[] params = new Object[]{"UserInfo", "*", "email", email};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        Integer user_id = -1;
        if (data.size() > 0) {
            user_id = Integer.parseInt(data.get(0).get("user_id").toString());
            System.out.println(user_id);
            return user_id;
        }
        else {
            return -1;
        }
    }

    public boolean validateSecurityAnswer(int user_id, String securityQuestion, String securityAnswer) throws Exception {
        Object[] params = new Object[]{"UserSensitive", "*", "user_id", user_id};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        if (data.size() > 0) {
            String secretQuestionFromDB = data.get(0).get("security_question").toString();
            String secretAnswerFromDB = data.get(0).get("security_answer").toString();
            System.out.println("secret question from DB : " + secretQuestionFromDB);
            System.out.println("secret answer from DB : " + secretAnswerFromDB);
            if (secretQuestionFromDB.equals(securityQuestion)) {
                if (secretAnswerFromDB.equals(securityAnswer)) {
                    return true;
                }
                else {return false;}
            } else {return false;}
        }
        return false;
    }
}


