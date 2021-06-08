package utils.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * This is the utils class for hasing the password
 */
// https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#:~:text=PBKDF2%2C%20BCrypt%2C%20SCrypt-,Java%20Secure%20Hashing%20%E2%80%93%20MD5%2C%20SHA256%2C%20SHA512%2C%20PBKDF2%2C,weak%20and%20easy%20to%20guess.
public class Hash {
    public static String getHashedPassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = bytesToString(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // store salt as string in database
    private static String bytesToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
