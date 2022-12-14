CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `updateTwoColumnDBUsingWhere`(IN table_name varchar(50),  IN criteria_where_column_name varchar(50), IN otp INT, IN otp_time BIGINT(50), IN criteria_where_column_value INT)
BEGIN

	set @query = CONCAT('update CSCI5308_11_DEVINT.', table_name, ' set otp = ', otp, ', otp_time = ', otp_time, ' where ', criteria_where_column_name, ' = \'', criteria_where_column_value, '\';');
	
    prepare stmt from @query; 
	execute stmt;

	DEALLOCATE PREPARE stmt;
    
END