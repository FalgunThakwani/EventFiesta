package group11.EventFiesta.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class EncryptData {

    private static final Random RANDOM = new SecureRandom();

    public static String encryptData(String data, String salt){
        String encryptedData = "-";
        byte[] dataArray = convertToByteArray(data);
        byte[] saltArray = convertToByteArray(salt);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dataArray);
            md.update(saltArray);
            byte[] cipher = md.digest();
            encryptedData = convertToString(cipher);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return encryptedData;
    }

    private static String convertToString(byte[] byteArray) {
        return Arrays.toString(byteArray);
    }

    private static byte[] convertToByteArray(String input) {
        byte[] byteArray = input.getBytes(StandardCharsets.UTF_8);
        return byteArray;
    }

    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return convertToString(salt);
    }
}
