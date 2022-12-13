package group11.EventFiesta.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptDataTest {

    @Test
    public void getEncryptedDataSuccessTest() {
        String salt = EncryptData.getNextSalt();
        String pwd = "1234";
        String pwdFromDB = EncryptData.encryptData(pwd, salt);
        String encPwd = EncryptData.encryptData(pwd, salt);
        Assertions.assertEquals(encPwd, pwdFromDB);
    }

    @Test
    public void getEncryptedDataMismatchFailureTest() {
        String salt = EncryptData.getNextSalt();
        String data = "1234";
        String encryptedData = EncryptData.encryptData(data, salt);
        String changedData = "123";
        String encryptedChangedData = EncryptData.encryptData(changedData, salt);
        Assertions.assertNotEquals(encryptedChangedData, encryptedData);
    }

    @Test
    public void getEncryptedSaltMismatchFailureTest() {
        String salt = EncryptData.getNextSalt();
        String data = "1234";
        String encryptedData = EncryptData.encryptData(data, salt);
        String changedSalt = EncryptData.getNextSalt();
        String encryptedChangedSaltData = EncryptData.encryptData(data, changedSalt);
        Assertions.assertNotEquals(encryptedChangedSaltData, encryptedData);
    }
}
