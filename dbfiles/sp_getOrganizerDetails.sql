CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getOrganizerDetails`(IN organizerID int)
BEGIN
Select * from OrganizerInfo where organizer_id=organizerID;
END