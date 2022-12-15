package group11.EventFiesta.db;

import java.util.*;

public class MockEventManagerDBPersistence implements IDBPersistence {

    @Override
    public List<Map<String, Object>> loadData(String query) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        List<Map<String, Object>> mock = new ArrayList<>();
        if (storedProcedure.equals("getOrganizerEventDetails")) {

            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{1, "Upcoming"}))) {
                //getEventServicesSuccessTest

                Map map = new HashMap();
                map.put("event_id", 1);
                mock.add(map);

                map = new HashMap();
                map.put("event_id", 2);
                mock.add(map);

            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{1, "Complete"}))) {
                //getEventServicesCompletedReviewTest

                Map map = new HashMap();
                map.put("event_id", 3);
                mock.add(map);

            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{2, "Complete"}))) {
                //getEventServicesCompletedNoReviewTest

                Map map = new HashMap();
                map.put("event_id", 4);
                mock.add(map);
            }

        } else if (storedProcedure.equals("getOrganizerEventServices")) {

            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{1, "Upcoming", 1}))) {
                //getEventServicesSuccessTest

                Map map = new HashMap();
                map.put("service_id", 1);
                mock.add(map);

                map = new HashMap();
                map.put("service_id", 11);
                mock.add(map);

            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{2, "Upcoming", 1}))) {
                //getEventServicesSuccessTest

                Map map = new HashMap();
                map.put("service_id", 2);
                mock.add(map);

                map = new HashMap();
                map.put("service_id", 22);
                mock.add(map);

            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{3, "Complete", 1}))) {
                //getEventServicesCompletedReviewTest

                Map map = new HashMap();
                map.put("service_id", 3);
                mock.add(map);

            } else if (Arrays.toString(params).equals(Arrays.toString(new Object[]{4, "Complete", 2}))) {
                //getEventServicesCompletedNoReviewTest

                Map map = new HashMap();
                map.put("service_id", 4);
                mock.add(map);

            }
        } else if (storedProcedure.equals("getServiceReviews")) {

            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{3, 3}))) {
                //getEventServicesCompletedReviewTest

                Map map = new HashMap();
                map.put("review", "Good review");
                map.put("rating", "5");
                mock.add(map);

            }
        }

        return mock;
    }

    @Override
    public Integer saveData(String query, Object... params) throws Exception {
        return null;
    }

    @Override
    public Integer updateData(String storedProcedure, Object... params) throws Exception {

        if (storedProcedure.equals("updateEventStatus")) {

            if (Arrays.toString(params).equals(Arrays.toString(new Object[]{1, 1, "Upcoming", "Pending"}))) {
                //updateEventSuccessTest
                return 1;

            }
        }
        return 0;
    }

    @Override
    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        List<Object> mock = new ArrayList<>();
        if (insertProcedure.equals("addEvent")) {
            mock.add(1);
        } else if (insertProcedure.equals("addService")) {
            mock.add(1);
        }
        return mock;
    }
}
