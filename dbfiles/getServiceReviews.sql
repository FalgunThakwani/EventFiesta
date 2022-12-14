CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getServiceReviews`(IN e_id INT, IN s_id INT)
BEGIN
	select review, rating from ServiceReviews where event_id = e_id and service_id = s_id;
END