CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `updateDBUsingWhere`(IN table_name varchar(50), IN criteria_set_column_name varchar(50), IN criteria_set_column_value varchar(1000), IN criteria_where_column_name varchar(50), IN criteria_where_column_value varchar(50))
BEGIN

	set @query = CONCAT('update CSCI5308_11_DEVINT.', table_name, ' set ', criteria_set_column_name, ' = \'', criteria_set_column_value, '\' where ', criteria_where_column_name, ' = \'', criteria_where_column_value, '\';');
	
    prepare stmt from @query; 
	execute stmt;

	DEALLOCATE PREPARE stmt;
    
END