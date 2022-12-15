package group11.EventFiesta.OrganizerSignUpTest;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockOrganizerSignUpDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.signUp.ISignup;
import group11.EventFiesta.signUp.OrganizerSignUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrganizerSignUpTest {
    private IDBPersistence dbPersistence = new MockOrganizerSignUpDBPersistence();
    ISignup signupTest = new OrganizerSignUp(dbPersistence);
    private Account org = new Organizer();

  @Test
  public void validateOrganizer() throws Exception {
    org.setEmail("dp860359@dal.ca");
    Assertions.assertEquals(signupTest.validateUser(org), true);
  }

  @Test
  public void validateWrongOrganizer() throws Exception {
    org.setEmail("deepknaik641@gmail.com");
    Assertions.assertEquals(signupTest.validateUser(org), false);
  }
}
