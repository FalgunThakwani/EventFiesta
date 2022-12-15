package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockUserGuestListDBPersistence;
import group11.EventFiesta.model.UserGuestList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuestListHandlerTest {

IDBPersistence idbPersistence = new MockUserGuestListDBPersistence();

  @Test
  public void loadGuestListSuccessTest() throws Exception {
    GuestListHandler guestListHandler = new GuestListHandler(idbPersistence);
    UserGuestList ugl = guestListHandler.loadGuestList(1);
    Assertions.assertEquals(ugl != null, true);
  }

  @Test
  public void loadGuestListFailureTest() throws Exception {
    GuestListHandler guestListHandler = new GuestListHandler(idbPersistence);
    UserGuestList ugl = guestListHandler.loadGuestList(2);
    Assertions.assertEquals(ugl.getGuests().size() != 0, false);
  }

}
