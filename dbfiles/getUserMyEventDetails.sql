CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getUserMyEventDetails`(IN criteria_column_value varchar(50))
BEGIN
	select 
		ed.event_id, 
        ed.type, 
        ed.venue, 
        ed.event_date, 
        ed.total_cost, 
        ed.head_count, 
        es.service_id, 
        es.cost, 
		od.service_type, 
        od.organizer_id, 
        oi.first_name, 
        oi.last_name, 
        oi.email, 
        oi.phone_number
	from 
		CSCI5308_11_DEVINT.EventDetails ed 
		join CSCI5308_11_DEVINT.EventServices es on (ed.event_id = es.event_id)
		join CSCI5308_11_DEVINT.OrganizerDetails od on (es.service_id = od.service_id)
		join CSCI5308_11_DEVINT.OrganizerInfo oi on (oi.organizer_id = od.organizer_id)
	where 
		ed.event_id = criteria_column_value;
END