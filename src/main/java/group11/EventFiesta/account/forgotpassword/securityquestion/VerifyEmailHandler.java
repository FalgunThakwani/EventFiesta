package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Account;

import java.util.List;
import java.util.Map;

public class VerifyEmailHandler {

    Object[] params;
    IDBPersistence idbPersistence;

    public VerifyEmailHandler(IDBPersistence idbPersistence, Object [] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }

    public List<Map<String, Object>> validateEmail(Account account) throws Exception {

        System.out.println("Params: ");

        for(Object o: params)
            System.out.println(String.valueOf(o));

        List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(params);
        System.out.println(data);
        if (data.size() > 0) {
            return data;
        }
        else {
            return null;
        }
    }
}
