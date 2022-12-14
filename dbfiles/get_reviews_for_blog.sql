CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `get_reviews_for_blog`()
BEGIN

	SELECT review_id, rating, review, service_id, event_id FROM CSCI5308_11_DEVINT.ServiceReviews;

END