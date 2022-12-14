package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Reviews;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BlogHandler
{
    public List<Reviews> getReviewList(IDBPersistence db)
    {
        List<Reviews> reviewsList = new LinkedList<>();

        try
        {
            List<Map<String, Object>> map = db.loadData("SELECT review_id, rating, review, service_id, event_id FROM CSCI5308_11_DEVINT.ServiceReviews");

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
