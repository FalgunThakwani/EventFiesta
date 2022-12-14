package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockResetPassword implements IDBPersistence {

    List<Map<String, Object>> userList;

    public MockResetPassword()
    {
        userList = new ArrayList<>();

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setEmail("test@test.com");

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());

        userList.add(map);
    }


    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        return null;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        return null;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {

        int user_id = (Integer) params[4];

        for (int i = 0; i < userList.size(); i++) {
            Map<String, Object> map = userList.get(i);

            if (map.containsKey("user_id") && (Integer) map.get("user_id") == user_id) {
                userList.get(i).put("encrypted_password", 0);
                return 1;
            }
            else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        return null;
    }
}
