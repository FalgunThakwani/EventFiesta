package group11.EventFiesta;

import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public class EncryptPassword {

    private static final Random RANDOM = new SecureRandom();

    private static String ENCODING_FORMAT = "ISO-8859-1";

    private static byte encoding[] = {
            (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D',
            (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H',
            (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L',
            (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P',
            (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T',
            (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X',
            (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b',
            (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f',
            (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j',
            (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
            (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r',
            (byte) 's', (byte) 't', (byte) 'u', (byte) 'v',
            (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z',
            (byte) '0', (byte) '1', (byte) '2', (byte) '3',
            (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) '+', (byte) '/',
            (byte) '='
    };

    private static int byte1(byte buf[], int offset) {
        return (buf[offset] & 0xfc) >> 2 ;
    }

    private static int byte2(byte buf[], int offset) {
        return ((buf[offset]&0x3) << 4) | ((buf[offset+1]&0xf0) >>> 4) ;
    }

    private static int byte3(byte buf[], int offset) {
        return ((buf[offset+1] & 0x0f) << 2) | ((buf[offset+2] & 0xc0) >>> 6) ;
    }

    private static int byte4(byte buf[], int offset) {
        return buf[offset+2] & 0x3f ;
    }

    private static void process(InputStream in, OutputStream out) throws IOException {
        byte buffer [] = new byte[1024];
        int read = -1;
        int offset = 0;
        int count = 0;
        while ((read = in.read(buffer, offset, 1024 - offset)) > 0) {
            if(read >= 3) {
                read += offset;
                offset  = 0;
                while (offset + 3 <= read) {
                    int c1 = byte1(buffer,offset);
                    int c2 = byte2(buffer,offset);
                    int c3 = byte3(buffer,offset);
                    int c4 = byte4(buffer,offset);
                    switch (count) {
                        case 73:
                            out.write(encoding[c1]);
                            out.write(encoding[c2]);
                            out.write(encoding[c3]);
                            out.write('\n') ;
                            out.write(encoding[c4]);
                            count = 1 ;
                            break ;
                        case 74:
                            out.write(encoding[c1]);
                            out.write(encoding[c2]);
                            out.write('\n') ;
                            out.write(encoding[c3]);
                            out.write(encoding[c4]) ;
                            count = 2 ;
                            break ;
                        case 75:
                            out.write(encoding[c1]);
                            out.write('\n') ;
                            out.write(encoding[c2]);
                            out.write(encoding[c3]);
                            out.write(encoding[c4]) ;
                            count = 3 ;
                            break ;
                        case 76:
                            out.write('\n') ;
                            out.write(encoding[c1]);
                            out.write(encoding[c2]);
                            out.write(encoding[c3]);
                            out.write(encoding[c4]);
                            count = 4;
                            break;
                        default:
                            out.write(encoding[c1]);
                            out.write(encoding[c2]);
                            out.write(encoding[c3]);
                            out.write(encoding[c4]);
                            count += 4;
                            break;
                    }
                    offset += 3;
                }
                // Copy remaining bytes to beginning of buffer:
                for ( int i = 0 ; i < 3 ;i++) {
                    buffer[i] = (i < read-offset) ? buffer[offset+i] : ((byte) 0);
                }
                offset = read-offset ;
            }
            else {
                // Total read amount is less then 3 bytes:
                offset += read;
            }
        }
        // Manage the last bytes, from 0 to offset:
        switch (offset) {
            case 1:
                out.write(encoding[byte1(buffer, 0)]);
                out.write(encoding[byte2(buffer, 0)]);
                out.write('=');
                out.write('=');
                break;
            case 2:
                out.write(encoding[byte1(buffer, 0)]);
                out.write(encoding[byte2(buffer, 0)]);
                out.write(encoding[byte3(buffer, 0)]);
                out.write('=');
        }
        return;
    }

    private static byte[] convertToByteArray(String str) {
        byte[] pass = null;
        try {
            pass = str.getBytes(ENCODING_FORMAT);
        } catch (Exception e) {
            pass = str.getBytes();
        }
        return pass;
    }

    private static String convertToString(byte[] input) {
        String str = null;
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new ByteArrayInputStream(input);
            outputStream = new ByteArrayOutputStream();
            process(inputStream, outputStream);
            str = outputStream.toString(ENCODING_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String getEncryptedPwd(String pwd, String salt){
        String encPwd = "-";
        byte[] password_ba = convertToByteArray(pwd);
        byte[] salt_ba = convertToByteArray(salt);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password_ba);
            md.update(salt_ba);
            byte[] cipher = md.digest();
            encPwd = convertToString(cipher);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return encPwd;
    }

    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return convertToString(salt);
    }
}
