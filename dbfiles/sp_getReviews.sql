CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getReviews`(IN serviceId INT)
BEGIN
Select * from ServiceReviews where service_id=serviceId;
END