CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getServiceHistory`(IN serviceID int)
BEGIN
select count(service_id) from EventServices where service_id=serviceID AND status="Complete";
END