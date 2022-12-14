CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getOrganizerEventDetails`(IN org_id varchar(50), IN stat varchar(50))
BEGIN
	select EventDetails.event_id, EventDetails.type, EventDetails.venue, EventDetails.head_count, 
    EventDetails.event_date, UserInfo.first_name, UserInfo.last_name, UserInfo.email from EventDetails 
    INNER JOIN UserInfo ON EventDetails.user_id = UserInfo.user_id 
    where EventDetails.event_id in (select distinct(EventServices.event_id) from EventServices 
    where EventServices.status = stat and EventServices.service_id in (select service_id from OrganizerDetails where organizer_id = org_id));
END