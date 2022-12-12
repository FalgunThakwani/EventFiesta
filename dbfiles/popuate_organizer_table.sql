insert into CSCI5308_11_DEVINT.OrganizerInfo (name, first_name, last_name, email, address, phone_number) values ("organizer1", "organizer1", "organizer1", "organizer1@gmail.com", "address 1", "445");
insert into CSCI5308_11_DEVINT.OrganizerInfo (name, first_name, last_name, email, street_address, province, city, phone_number) values ("organizer2", "organizer2", "organizer2", "organizer2@gmail.com", "address 1", "", "Halifax", "445");


insert into CSCI5308_11_DEVINT.OrganizerSensitive (organizer_id, security_question, security_answer, number, sign_up_date, last_login, encrypted_password, private_key, private_key_expiry, islogout)
values (1,"What is your DOB?" ,"1st April 1995" ,"999" , now(), now(), "lvpEQgg0Woy8l5Wdu0JcZA==", "ixtcdcRWGT4XOgNx9Q7QJA==", now(), 1)


insert into OrganizerDetails (service_type, description, max_head_count, organizer_id, price, unit) values ('catering', '', 100, 1, 100, 1);
insert into OrganizerDetails (service_type, description, max_head_count, organizer_id, price, unit) values ('decoration', '', 200, 2, 90, 1);
insert into OrganizerDetails (service_type, description, max_head_count, organizer_id, price, unit) values ('auditorium', '', 200, 2, 110, 1);