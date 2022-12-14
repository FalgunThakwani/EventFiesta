package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockOTPDBPersistence;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GenerateOTPTest {

    @Test
    public void generateOrganizerOTPSuccessTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
                    when(mock.sendMail(any())).thenReturn(true);
            })) {

            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account organizer = new Organizer();
            organizer.setEmail("generateOTP@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
            paramList.add(params);
            params = new Object[]{"OrganizerSensitive", "organizer_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(organizer, paramList);
            Assertions.assertEquals("OTP has been sent", accountState.getStatus());
            Assertions.assertEquals("OrganizerEnterOTP", accountState.getNextPage());

        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }

    @Test
    public void generateOrganizerOTPMailNotSentTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
            when(mock.sendMail(any())).thenReturn(false);
        })) {

            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account organizer = new Organizer();
            organizer.setEmail("generateOTP@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
            paramList.add(params);
            params = new Object[]{"OrganizerSensitive", "organizer_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(organizer, paramList);
            Assertions.assertEquals("Mail could not be sent due to an error", accountState.getStatus());
            Assertions.assertEquals("redirect:/organizerForgotPassword", accountState.getNextPage());

        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }

    @Test
    public void generateOrganizerOTPInvalidAccountTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
            when(mock.sendMail(any())).thenReturn(true);
        })) {

            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account organizer = new Organizer();
            organizer.setEmail("invalidAccount@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
            paramList.add(params);
            params = new Object[]{"OrganizerSensitive", "organizer_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(organizer, paramList);
            Assertions.assertEquals("Invalid account", accountState.getStatus());
            Assertions.assertEquals("OrganizerLogin", accountState.getNextPage());

        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }

    @Test
    public void generateUserOTPSuccessTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
                    when(mock.sendMail(any())).thenReturn(true);
            })) {

            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account user = new User();
            user.setEmail("generateOTP@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};
            paramList.add(params);
            params = new Object[]{"UserSensitive", "user_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(user, paramList);
            Assertions.assertEquals("OTP has been sent", accountState.getStatus());
            Assertions.assertEquals("UserEnterOTP", accountState.getNextPage());

        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }

    @Test
    public void generateUserOTPMailNotSentTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
            when(mock.sendMail(any())).thenReturn(false);
        })) {

            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account user = new User();
            user.setEmail("generateOTP@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};
            paramList.add(params);
            params = new Object[]{"UserSensitive", "user_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(user, paramList);
            Assertions.assertEquals("Mail could not be sent due to an error", accountState.getStatus());
            Assertions.assertEquals("redirect:/userForgotPassword", accountState.getNextPage());

        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }

    @Test
    public void generateUserOTPInvalidAccountTest() {
        try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
            when(mock.sendMail(any())).thenReturn(true);
        })) {
            IDBPersistence idbPersistence = new MockOTPDBPersistence();
            Account user = new User();
            user.setEmail("invalidAccount@gmail.com");
            List<Object[]> paramList = new ArrayList<>();
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};
            paramList.add(params);
            params = new Object[]{"UserSensitive", "user_id"};
            paramList.add(params);
            GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
            IState accountState = generateOTP.generateOTP(user, paramList);
            Assertions.assertEquals("Invalid account", accountState.getStatus());
            Assertions.assertEquals("UserLogin", accountState.getNextPage());
        } catch (Exception exception) {
            System.out.println("Exception while mocking mail sendMail");
        }
    }
}
