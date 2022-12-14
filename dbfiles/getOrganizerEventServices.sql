CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getOrganizerEventServices`(IN e_id INT, IN stat varchar(50), IN o_id INT)
BEGIN
	select EventServices.service_id, EventServices.event_id, OrganizerDetails.service_type, 
    EventServices.status, EventServices.cost from EventServices 
    INNER JOIN OrganizerDetails ON EventServices.service_id = OrganizerDetails.service_id 
    where EventServices.event_id = e_id and EventServices.status = stat and EventServices.service_id in 
    (select service_id from OrganizerDetails where organizer_id = o_id);
END