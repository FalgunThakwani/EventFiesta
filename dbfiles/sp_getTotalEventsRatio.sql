CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getTotalEventsRatio`(IN event_id nvarchar(45))
BEGIN
SELECT Count(event_id) from EventServices;
END