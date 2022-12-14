package group11.EventFiesta.UserMyEventDetailsTest;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.event.UserMyEventDetails;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.UserEvent;
import group11.EventFiesta.model.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserMyEventDetailsTest {

    @Test
    public void getUserMyEventDetailsTest()
    {
        IDBPersistence mockDB = new MockUserMyEventDetailsPersistence();
        UserMyEventDetails userMyEventDetails = new UserMyEventDetails(mockDB);


        try
        {

            UserEvent userEvent = userMyEventDetails.getUserMyEventDetails(1);

            Assertions.assertEquals(2, userEvent.getEventID());
            Assertions.assertEquals("Wedding", userEvent.getEventType());
            Assertions.assertEquals("Halifax", userEvent.getEventVenue());
            Assertions.assertEquals(100, userEvent.getGuestCount());

            Assertions.assertEquals(3, userEvent.getService().size());

            List<UserService> service = userEvent.getService();
            UserService service1 = service.get(0);

                Assertions.assertEquals(1,service1.getId());
                Assertions.assertEquals("decoration", service1.getType());

                Organizer organizer = service1.getOrganizer();

                Assertions.assertEquals(1, organizer.getOrganizerId());
                Assertions.assertEquals("Ruchika", organizer.getFirstName());
                Assertions.assertEquals("Nagpal", organizer.getLastName());
                Assertions.assertEquals("ruchika@gmail.com", organizer.getEmail());

            UserService service2 = service.get(1);

                Assertions.assertEquals(2,service2.getId());
                Assertions.assertEquals("catering", service2.getType());

                organizer = service2.getOrganizer();

                Assertions.assertEquals(2, organizer.getOrganizerId());
                Assertions.assertEquals("Deep", organizer.getFirstName());
                Assertions.assertEquals("Naik", organizer.getLastName());
                Assertions.assertEquals("deep@gmail.com", organizer.getEmail());

            UserService service3 = service.get(2);

                Assertions.assertEquals(3,service3.getId());
                Assertions.assertEquals("hall", service3.getType());

                organizer = service3.getOrganizer();

                Assertions.assertEquals(1, organizer.getOrganizerId());
                Assertions.assertEquals("Ruchika", organizer.getFirstName());
                Assertions.assertEquals("Nagpal", organizer.getLastName());
                Assertions.assertEquals("ruchika@gmail.com", organizer.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getUserMyEventDetailsFailureTest()
    {
        IDBPersistence mockDB = new MockUserMyEventDetailsPersistence();
        UserMyEventDetails userMyEventDetails = new UserMyEventDetails(mockDB);

        try
        {
            UserEvent userEvent = userMyEventDetails.getUserMyEventDetails(3);

            Assertions.assertNotEquals(3, userEvent.getEventID());
            Assertions.assertNotEquals("Birthday", userEvent.getEventType());
            Assertions.assertNotEquals("Sydney", userEvent.getEventVenue());
            Assertions.assertNotEquals(150, userEvent.getGuestCount());

            Assertions.assertNotEquals(2, userEvent.getService().size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
