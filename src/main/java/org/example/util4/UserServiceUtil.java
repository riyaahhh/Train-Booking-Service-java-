package org.example.util4;
import org.mindrot.jbcrypt.BCrypt;
public class UserServiceUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String password1, String password2) {
        return BCrypt.checkpw(password1, password2);
    }
}
