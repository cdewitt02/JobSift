import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryption {

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkPassword(String enteredPassword, String storedEncryptedPassword) {
        String enteredEncryptedPassword = encryptPassword(enteredPassword);
        return enteredEncryptedPassword.equals(storedEncryptedPassword);
    }


}
