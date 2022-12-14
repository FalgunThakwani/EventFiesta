CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_storeUserMyEventFeedback`(
IN _rating int,
IN _review varchar(10000),
IN _service_id int,
IN _event_id int
)
BEGIN
INSERT INTO `CSCI5308_11_DEVINT`.`ServiceReviews` (`rating`, `review`, `service_id`, `event_id`) VALUES (_rating, _review, _service_id, _event_id);
END