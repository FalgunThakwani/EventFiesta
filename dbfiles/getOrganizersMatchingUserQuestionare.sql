CREATE DEFINER=`CSCI5308_11_DEVINT_USER`@`%` PROCEDURE `getOrganizersMatchingUserQuestionare`(IN city varchar(50), IN services varchar(100), IN budget_l decimal, IN budget_u decimal,IN guestCount int)
BEGIN
SELECT OrganizerInfo.organizer_id, OrganizerInfo.name, OrganizerDetails.service_id, OrganizerDetails.service_type,
OrganizerDetails.price FROM OrganizerInfo
INNER JOIN OrganizerDetails ON OrganizerDetails.organizer_id = OrganizerInfo.organizer_id
 where OrganizerInfo.city = city and
        FIND_IN_SET(service_type, services) > 0 and price*guestCount<=budget_l and price*guestCount<=budget_u;
END