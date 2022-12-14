CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `addEvent`(IN userId INT, IN etype varchar(50), IN evenue varchar(50),IN edate varchar(50), IN ecost INT, IN ecount INT, OUT eventId INT)
BEGIN
	insert into EventDetails (user_id, type, venue, event_date, total_cost, head_count) values (userId, etype, evenue, edate, ecost, ecount);
	set eventId = last_insert_id();
END

CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `updateEventStatus`(IN e_id INT, IN o_id INT, IN stat varchar(50))
BEGIN
	update EventServices set status = stat where status = "Pending" and event_id = e_id and
    service_id in (select service_id from OrganizerDetails where organizer_id = o_id);
END