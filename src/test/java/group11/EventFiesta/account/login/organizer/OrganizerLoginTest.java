package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockOrganizerLoginDBPersistence;
import group11.EventFiesta.model.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class OrganizerLoginTest {

    @Test
    public void successLoginTest() {
        try {
            HttpServletRequest request = new MockHttpServletRequest();
            IDBPersistence mySQLDBPersistence = new MockOrganizerLoginDBPersistence();

            Organizer organizer = new Organizer();
            organizer.setEmail("sreedevi.rw@gmail.com");
            organizer.setPassword("1234");
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};

            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(request);
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accounCheckHandler.execute(organizer);

            Assertions.assertEquals(state.getNextPage(), "redirect:/getOrganizerDetails");
            Assertions.assertEquals(state.getStatus(), "Successfully logged in");
        } catch (Exception exception) {
            System.out.println("Exception in OrganizerLoginTest.successLoginTest() : " + exception.getMessage());
        }
    }

    @Test
    public void loginFailureInvalidAccountTest() {
        try {
            HttpServletRequest request = new MockHttpServletRequest();
            IDBPersistence mySQLDBPersistence = new MockOrganizerLoginDBPersistence();

            Organizer organizer = new Organizer();
            organizer.setEmail("sreedevi.w@gmail.com");
            organizer.setPassword("1234");
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};

            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(request);
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accounCheckHandler.execute(organizer);

            Assertions.assertEquals(state.getNextPage(), "OrganizerLogin");
            Assertions.assertEquals(state.getStatus(), "Invalid account");
        } catch (Exception exception) {
            System.out.println("Exception in OrganizerLoginTest.successLoginTest() : " + exception.getMessage());
        }
    }

    @Test
    public void loginFailureWrongPasswordTest() {
        try {
            HttpServletRequest request = new MockHttpServletRequest();
            IDBPersistence mySQLDBPersistence = new MockOrganizerLoginDBPersistence();

            Organizer organizer = new Organizer();
            organizer.setEmail("sreedevi.rw@gmail.com");
            organizer.setPassword("123");
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};

            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(request);
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accounCheckHandler.execute(organizer);

            Assertions.assertEquals(state.getNextPage(), "organizerLogin");
            Assertions.assertEquals(state.getStatus(), "Entered password is incorrect");
        } catch (Exception exception) {
            System.out.println("Exception in OrganizerLoginTest.successLoginTest() : " + exception.getMessage());
        }
    }
}
