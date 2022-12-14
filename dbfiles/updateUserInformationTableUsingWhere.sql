CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `updateUserInformationTableUsingWhere`(
	pass varchar(500), user_email varchar(50)
)
BEGIN

	update UserSensitive set encrypted_password = pass where user_id = (select user_id from UserInfo where email = user_email);

END