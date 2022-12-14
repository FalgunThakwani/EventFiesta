package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.security.EncryptData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenerateNewEncryptedPasswordTest {

    @Test
    public void getEncryptedPasswordPassTest()
    {
        String originalSalt = "[71, -50, -58, 73, -51, -95, -14, 41, 22, 116, -100, -43, -58, 61, 38, 56]";
        String originalPassword = "1234";
        String originalEncryptedPassword = "[64, 82, -28, 9, -41, -67, -60, 81, -48, 86, 91, -32, 110, 28, -1, 73]";

        String mockSalt = "[71, -50, -58, 73, -51, -95, -14, 41, 22, 116, -100, -43, -58, 61, 38, 56]";
        String mockPassword = "1234";

        String generatedEncryptedPassword = EncryptData.encryptData(mockPassword, mockSalt);

        Assertions.assertEquals(originalEncryptedPassword, generatedEncryptedPassword);
    }

    @Test
    public void getEncryptedPasswordFailInvalidSaltTest()
    {
        String originalSalt = "[71, -50, -58, 73, -51, -95, -14, 41, 22, 116, -100, -43, -58, 61, 38, 56]";
        String originalPassword = "1234";
        String originalEncryptedPassword = "[64, 82, -28, 9, -41, -67, -60, 81, -48, 86, 91, -32, 110, 28, -1, 73]";

        String mockSalt = "[80, -50, -58, 73, -51, -95, -14, 41, 90, 116, -100, -43, -58, 61, 38, 56]";
        String mockPassword = "1234";

        String generatedEncryptedPassword = EncryptData.encryptData(mockPassword, mockSalt);

        Assertions.assertNotEquals(originalEncryptedPassword, generatedEncryptedPassword);
    }

    @Test
    public void getEncryptedPasswordFailInvalidPasswordTest()
    {
        String originalSalt = "[71, -50, -58, 73, -51, -95, -14, 41, 22, 116, -100, -43, -58, 61, 38, 56]";
        String originalPassword = "1234";
        String originalEncryptedPassword = "[64, 82, -28, 9, -41, -67, -60, 81, -48, 86, 91, -32, 110, 28, -1, 73]";

        String mockSalt = "[71, -50, -58, 73, -51, -95, -14, 41, 22, 116, -100, -43, -58, 61, 38, 56]";
        String mockPassword = "12";

        String generatedEncryptedPassword = EncryptData.encryptData(mockPassword, mockSalt);

        Assertions.assertNotEquals(originalEncryptedPassword, generatedEncryptedPassword);
    }
}
