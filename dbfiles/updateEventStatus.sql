CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `updateEventStatus`(IN e_id INT, IN o_id INT, IN stat varchar(50))
BEGIN
	update EventServices set status = stat where status = "Pending" and event_id = e_id and
    service_id in (select service_id from OrganizerDetails where organizer_id = o_id);
END