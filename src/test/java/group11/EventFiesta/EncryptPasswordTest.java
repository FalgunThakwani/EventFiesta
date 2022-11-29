package group11.EventFiesta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptPasswordTest {

    @Test
    public void getEncryptedPwd() {
        String pwdFromDB = "3DBOFG449Z34j7dTYS+hDA==";
        String saltFromDB = "UHbtPc3UnowVcCCd6I+v1g==";
        String pwd = "abc";
        System.out.println(saltFromDB);
        String encPwd = EncryptPassword.getEncryptedPwd(pwd, saltFromDB);
        System.out.println(encPwd);
        Assertions.assertEquals(encPwd, pwdFromDB);
    }
}
