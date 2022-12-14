CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `sp_storeOrganizerData`(
IN _name varchar(45),
IN _firstName varchar(45),
IN _lastName varchar(45),
IN _email varchar(45),
IN _encryptedPassword varchar(240),
IN _privateKey varchar(240),
IN _privateKeyExpiry Date,
IN _businessNo int,
IN _signUpDate Date,
IN _lastLogin Date,
IN _address varchar(45),
IN _city varchar(45),
IN _province varchar(45),
IN _pincode varchar(45),
IN _fromContact Date,
IN _toContact Date,
IN _isLogout int,
IN _service1 varchar(45),
IN _cost1 int,
IN _service2 varchar(45),
IN _cost2 int,
IN _service3 varchar(45),
IN _cost3 int
)
BEGIN
DECLARE i INT DEFAULT 0;
INSERT INTO `CSCI5308_11_DEVINT`.`OrganizerInfo` (`name`, `first_name`, `last_name`, `email`, `street_address`, `province`, `city`, `pincode`, `contact_hours_from`, `contact_hours_to`) VALUES (_name, _firstName, _lastName, _email, _address, _province, _city, _pincode, _fromContact, _toContact);
set i = (select max(organizer_id) from OrganizerInfo);
INSERT INTO `CSCI5308_11_DEVINT`.`OrganizerSensitive` (`organizer_id`, `number`, `sign_up_date`, `last_login`, `encrypted_password`, `private_key`, `private_key_expiry`, `islogout`) values (i, _businessNo, _signUpDate, _lastLogin, _encryptedPassword, _privateKey, _privateKeyExpiry, _isLogout);

	IF _cost1 != 0 THEN
		Insert into OrganizerDetails(`service_type`, `description`, `organizer_id`, `price`) Values(_service1, _service1, i, _cost1);
	END IF;
    
    IF _cost2 != 0 THEN
		Insert into OrganizerDetails(`service_type`, `description`, `organizer_id`, `price`) Values(_service2, _service2, i, _cost2);
	END IF;
    
    IF _cost3 != 0 THEN
		Insert into OrganizerDetails(`service_type`, `description`, `organizer_id`, `price`) Values(_service3, _service3, i, _cost3);
	END IF;

END