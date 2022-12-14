CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `update_event_checklist`(item_id int)
BEGIN
	update CSCI5308_11_DEVINT.EventCheckList set `status` = 1 where checklist_item_id = item_id;
END