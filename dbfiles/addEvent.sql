CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `addEvent`(IN userId INT, IN etype varchar(50), IN evenue varchar(50),IN edate varchar(50), IN ecost INT, IN ecount INT, OUT eventId INT)
BEGIN
	insert into EventDetails (user_id, type, venue, event_date, total_cost, head_count) values (userId, etype, evenue, edate, ecost, ecount);
	set eventId = last_insert_id();
END