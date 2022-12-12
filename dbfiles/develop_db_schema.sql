CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`OrganizerInfo` (
  `organizer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`organizer_id`),
  UNIQUE INDEX `organizer_id_UNIQUE` (`organizer_id` ASC));

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`OrganizerSensitive` (
  `organizer_id` INT NOT NULL,
  `security_question` VARCHAR(240) NOT NULL,
  `security_answer` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `sign_up_date` DATE NOT NULL,
  `last_login` DATE NOT NULL,
  `encrypted_password` VARCHAR(45) NOT NULL,
  `private_key` VARCHAR(45) NOT NULL,
  `private_key_expiry` DATE NOT NULL,
  `otp` INT,
  `otp_time` BIGINT(50),

  PRIMARY KEY (`organizer_id`),
  INDEX `fk_OrganizerSensitive_OrganizerInfo1_idx` (`organizer_id` ASC),
  CONSTRAINT `fk_OrganizerSensitive_OrganizerInfo1`
    FOREIGN KEY (`organizer_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`OrganizerInfo` (`organizer_id`));


CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`UserInfo` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`UserSensitive` (
  `user_id` INT NOT NULL,
  `security_question` VARCHAR(240) NOT NULL,
  `security_answer` VARCHAR(45) NOT NULL,
  `sign_up_date` DATE NOT NULL,
  `last_login` DATE NOT NULL,
  `encrypted_password` VARCHAR(45) NOT NULL,
  `private_key` VARCHAR(45) NOT NULL,
  `private_key_expiry` DATE NOT NULL,
  `otp` INT,
  `otp_time` BIGINT(50),
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  INDEX `fk_UserSensitive_UserInfo_idx` (`user_id` ASC),
  CONSTRAINT `fk_UserSensitive_UserInfo`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`UserInfo` (`user_id`));


CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`EventDetails` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `venue` VARCHAR(45) NULL DEFAULT NULL,
  `event_date` DATETIME NULL DEFAULT NULL,
  `total_cost` INT NULL DEFAULT NULL,
  `head_count` INT NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_EventDetails_UserInfo1_idx` (`user_id` ASC),
  CONSTRAINT `fk_EventDetails_UserInfo1`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`UserInfo` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`EventServices` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `details` VARCHAR(45) NULL DEFAULT NULL,
  `organizer_id` INT NOT NULL,
  `cost` INT NULL DEFAULT NULL,
  `service_time` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  INDEX `fk_EventServices_UserEventDetails_idx` (`event_id` ASC),
  INDEX `fk_EventServices_OrganizerInfo1_idx` (`organizer_id` ASC),
  CONSTRAINT `fk_EventServices_UserEventDetails`
    FOREIGN KEY (`event_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`EventDetails` (`event_id`),
  CONSTRAINT `fk_EventServices_OrganizerInfo1`
    FOREIGN KEY (`organizer_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`OrganizerInfo` (`organizer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`Locations` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`OrganizerDetails` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `service_type` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `max_head_count` INT NULL DEFAULT NULL,
  `organizer_id` INT NOT NULL,
  PRIMARY KEY (`service_id`),
  INDEX `fk_OrganizerDetails_OrganizerInfo1_idx` (`organizer_id` ASC),
  CONSTRAINT `fk_OrganizerDetails_OrganizerInfo1`
    FOREIGN KEY (`organizer_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`OrganizerInfo` (`organizer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`OrganizerBranches` (
  `service_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`service_id`, `location_id`),
  INDEX `fk_OrganizerDetails_has_Locations_Locations1_idx` (`location_id` ASC),
  INDEX `fk_OrganizerDetails_has_Locations_OrganizerDetails1_idx` (`service_id` ASC),
  CONSTRAINT `fk_OrganizerDetails_has_Locations_Locations1`
    FOREIGN KEY (`location_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`Locations` (`location_id`),
  CONSTRAINT `fk_OrganizerDetails_has_Locations_OrganizerDetails1`
    FOREIGN KEY (`service_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`OrganizerDetails` (`service_id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`ServiceReviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL DEFAULT NULL,
  `review` VARCHAR(45) NULL DEFAULT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `fk_ServiceReviews_EventServices1_idx` (`service_id` ASC),
  CONSTRAINT `fk_ServiceReviews_EventServices1`
    FOREIGN KEY (`service_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`EventServices` (`service_id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`UserGuestList` (
  `user_guest_id` INT NOT NULL AUTO_INCREMENT,
  `user_guest_contact` INT NULL DEFAULT NULL,
  `event_id` INT NOT NULL,
  `invited` TINYINT NOT NULL,
  `rsvp` TINYINT NULL,
  PRIMARY KEY (`user_guest_id`),
  INDEX `fk_UserGuestList_EventDetails1_idx` (`event_id` ASC) ,
  CONSTRAINT `fk_UserGuestList_EventDetails1`
    FOREIGN KEY (`event_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`EventDetails` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `CSCI5308_11_DEVINT`.`EventCheckList` (
  `checklist_item_id`  INT NOT NULL AUTO_INCREMENT,
  `checklist_item_name` VARCHAR(45) NOT NULL,
  `event_id` INT NOT NULL,
  `completion_date` DATE NULL DEFAULT NULL,
  `status` TINYINT NOT NULL,
  INDEX `fk_EventCheckList_EventDetails1_idx` (`event_id` ASC),
  PRIMARY KEY (`checklist_item_id`),
  CONSTRAINT `fk_EventCheckList_EventDetails1`
    FOREIGN KEY (`event_id`)
    REFERENCES `CSCI5308_11_DEVINT`.`EventDetails` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
