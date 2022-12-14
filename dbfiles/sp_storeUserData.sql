CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_storeUserData`(
IN firstName varchar(45),
IN _lastName varchar(45),
IN _email varchar(45),
IN _securityQuestion varchar(240),
IN _securityAnswer varchar(45),
IN _signUpDate Date,
IN _lastLogin Date,
IN _encryptedPassword varchar(240),
IN _privateKey varchar(240),
IN _privateKeyExpiry Date,
IN _isLogout int

)
BEGIN
DECLARE i INT DEFAULT 0;
INSERT INTO `CSCI5308_11_DEVINT`.`UserInfo` (`first_name`, `last_name`, `email`) VALUES (firstName, _lastName, _email);
INSERT INTO UserSensitive (user_id,security_question,security_answer,sign_up_date,last_login,encrypted_password,private_key,private_key_expiry,isLogout)values ((select max(user_id) from UserInfo),_securityQuestion,_securityAnswer,_signUpDate,_lastLogin,_encryptedPassword,_privateKey,_privateKeyExpiry,_isLogout);
END