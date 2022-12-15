package group11.EventFiesta.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserSignUpDBPersistence implements IDBPersistence {

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {

        if (storedProcedure.equals("sp_checkUserExists")) {
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            if (params.equals("falgunthakwani@gmail.com")) {
                map.put("user_id", "2");
                map.put("email", "falgunthakwani@gmail.com");
                mock.add(map);
            }
            return mock;
        } else if (storedProcedure.equals("sp_storeUserData")) {
            List<Map<String, Object>> mock = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("firstName", "falgun");
            map.put("lastName", "thakwani");
            map.put("email", "falgun@gmail.com");
            map.put("password", "Awqedm32fsdf13245");
            map.put("key", "vdskmk3324");
            map.put("sign_up_date", "2022-01-30");
            map.put("last_login", "2022-01-30");
            map.put("isLogin", "0");
            map.put("security_question", "What is your father's middle name?");
            map.put("security_answer", "Jairaj");
            map.put("private_key_expiry", "2022-01-30");
            mock.add(map);
            return mock;
        } else {
            List<Map<String, Object>> mock = new ArrayList<>();
            return mock;
        }

    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
