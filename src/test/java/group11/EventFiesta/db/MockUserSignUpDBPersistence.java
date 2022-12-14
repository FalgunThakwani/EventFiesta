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
        List<Map<String, Object>> mock = new ArrayList<>();
        if (storedProcedure.equals("getFromDBUsingWhere")) {
            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"UserSensitive", "otp, otp_time", "user_id", 1}))) {
                //validateUserOTPSuccessTest()
                Map map = new HashMap();
                map.put("otp", 1111);
                map.put("otp_time", System.currentTimeMillis());
                mock.add(map);
            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"UserSensitive", "otp, otp_time", "user_id", 2}))) {
                //validateUserExpiredOTPTest
                Map map = new HashMap();
                map.put("otp", 1111);
                map.put("otp_time", 1111);
                mock.add(map);
            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", 1}))) {
                //validateOrganizerOTPSuccessTest()
                Map map = new HashMap();
                map.put("otp", 1111);
                map.put("otp_time", System.currentTimeMillis());
                mock.add(map);
            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", 2}))) {
                //validateOrganizerOTPTest
                Map map = new HashMap();
                map.put("otp", 1111);
                map.put("otp_time", 1111);
                mock.add(map);
            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"OrganizerInfo", "organizer_id", "email", "generateOTP@gmail.com"}))) {
                //generateOrganizerOTPSuccessTest
                Map map = new HashMap();
                map.put("organizer_id", 1);
                mock.add(map);
            }else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{"UserInfo", "user_id", "email", "generateOTP@gmail.com"}))) {
                //generateUserOTPSuccessTest
                Map map = new HashMap();
                map.put("user_id", 1);
                mock.add(map);
            }
        }
        return mock;
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
