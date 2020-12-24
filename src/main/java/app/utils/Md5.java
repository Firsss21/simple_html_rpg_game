package app.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Md5 {

    public static String crypt(String input)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b : digest)
                sb.append(Integer.toHexString((b&0xFF)|0x100), 1, 3);

            return sb.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Can't calculate MD5.");
            e.printStackTrace();
            return null;
        }
    }

}
