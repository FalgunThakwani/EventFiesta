package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MockSecurityPassword implements IDBPersistence
{
    List<Map<String, Object>> securityAccountList;

    public MockSecurityPassword()
    {
        securityAccountList = new LinkedList<>();
    }

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("testuser@test.com");
        user.setIsOrganizer(false);

        Map<String, Object> map = new HashMap<>();

        if(user.getEmail().equals(String.valueOf(params[3])))
        {
            map.put("testuser@test.com", user);
            securityAccountList.add(map);
        }
        else if(user.getUserId() == params[3])
        {
            map.put("testuser@test.com", user);
            map.put("security_question", "question");
            map.put("security_answer", "answer");

            securityAccountList.add(map);
        }

        System.out.println(securityAccountList);

        return securityAccountList;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        return null;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {
        return null;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        return null;
    }
}
