package group11.EventFiesta.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class EncryptPassword {

    private static final Random RANDOM = new SecureRandom();

    public static String getEncryptedPwd(String pwd, String salt){
        String encPwd = "-";
        byte[] pwdArray = convertToByteArray(pwd);
        byte[] saltArray = convertToByteArray(salt);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwdArray);
            md.update(saltArray);
            byte[] cipher = md.digest();
            encPwd = convertToString(cipher);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return encPwd;
    }

    private static String convertToString(byte[] byteArray) {
        return Arrays.toString(byteArray);
    }

    private static byte[] convertToByteArray(String input) {
        byte[] byteArray = input.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }

    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return convertToString(salt);
    }
}
