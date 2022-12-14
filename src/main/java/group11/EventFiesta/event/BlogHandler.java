package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Reviews;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BlogHandler
{
    IDBPersistence idbPersistence;

    Object[] params;
    public BlogHandler(IDBPersistence dbPersistence, Object[] params) {

        this.idbPersistence = dbPersistence;
        this.params = params;
    }
    public List<Reviews> getReviewList()
    {
        List<Reviews> reviewsList = new LinkedList<>();

        try
        {
            List<Map<String, Object>> map = idbPersistence.loadData("getFromDB", params);

            for(int i = 0; i < map.size(); i++)
            {
                Reviews review = new Reviews();
                Map<String, Object> row = map.get(i);

                review.setReviewId((Integer) row.get("review_id"));
                review.setRating((Integer) row.get("rating"));
                review.setReview((String) row.get("review"));
                review.setServiceId((Integer) row.get("service_id"));
                review.setEventId((Integer) row.get("event_id"));

                reviewsList.add(review);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return reviewsList;
    }
}
