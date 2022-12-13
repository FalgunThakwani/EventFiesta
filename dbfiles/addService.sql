CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `addService`(IN e_id INT, IN s_id INT, IN ecost INT, IN stat varchar(50))
BEGIN
	insert into EventServices (event_id, service_id, cost, status) values (e_id, s_id, ecost, stat);
END