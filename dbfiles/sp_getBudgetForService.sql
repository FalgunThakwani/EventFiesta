CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getBudgetForService`(IN serviceID int)
BEGIN
Select price from OrganizerDetails where service_id=ServiceID;
END