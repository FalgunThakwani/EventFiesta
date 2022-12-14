CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getRatings`(IN serviceId INT)
BEGIN
Select rating from ServiceReviews where service_id=serviceId;
END