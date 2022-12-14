CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_storeUserEventChecklistData`(
IN _checklist_item_name varchar(45),
IN _event_id int,
IN _completion_date Date,
IN _status boolean
)
BEGIN
INSERT INTO `CSCI5308_11_DEVINT`.`EventCheckList` (`checklist_item_name`, `event_id`, `completion_date`, `status`) VALUES (_checklist_item_name, _event_id, _completion_date, _status);
END