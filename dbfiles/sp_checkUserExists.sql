CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_checkUserExists`(Email nvarchar(60))
BEGIN
Select * from UserInfo where UserInfo.email=Email;
END