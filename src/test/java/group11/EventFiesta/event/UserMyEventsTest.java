package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockMyEventsDBPersistence;
import group11.EventFiesta.model.UserEvents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMyEventsTest {
  IDBPersistence idbPersistence = new MockMyEventsDBPersistence();
  @Test
  public void loadEventsSuccessTest(){
    UserMyEvents userMyEvents = new UserMyEvents(idbPersistence);

    ArrayList<UserEvents> result = new ArrayList<UserEvents>();

    {
      try {
        result = userMyEvents.loadEvents(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    boolean res = result != null;
    Assertions.assertEquals(res, true);
  }

  @Test
  public void loadEventsFailureTest(){
    UserMyEvents userMyEvents = new UserMyEvents(idbPersistence);

    ArrayList<UserEvents> result = new ArrayList<UserEvents>();

    {
      try {
        result = userMyEvents.loadEvents(2);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    Assertions.assertEquals(result.size()!=0, false);
  }
}
