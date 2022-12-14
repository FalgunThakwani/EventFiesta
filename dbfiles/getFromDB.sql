CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getFromDB`(IN table_name varchar(50), IN column_name varchar(50))
BEGIN

	set @query = CONCAT('SELECT ',column_name,' FROM CSCI5308_11_DEVINT.', table_name,';');
	prepare stmt from @query; 
	execute stmt;

	DEALLOCATE PREPARE stmt;
    
END