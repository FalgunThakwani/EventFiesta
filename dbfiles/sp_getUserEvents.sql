CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_getUserEvents`(IN user_id int)
BEGIN
	select 
		ed.event_id, 
        od.service_type,
        ed.type, 
        ed.venue, 
        ed.event_date, 
        ed.total_cost, 
        ed.head_count, 
        es.service_id, 
        es.cost,
        es.status
	from 
		CSCI5308_11_DEVINT.EventDetails ed 
		inner join CSCI5308_11_DEVINT.EventServices es on (ed.event_id = es.event_id)
        join CSCI5308_11_DEVINT.OrganizerDetails od on (es.service_id = od.service_id)
	where
		ed.user_id = user_id;
END