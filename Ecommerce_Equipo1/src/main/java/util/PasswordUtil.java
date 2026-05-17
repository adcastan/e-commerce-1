package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    private static final int COSTO = 12;

    public static String hash(String password) {
        return BCrypt.withDefaults().hashToString(COSTO, password.toCharArray());
    }

    public static boolean verificar(String password, String hash) {
        if (password == null || hash == null) {
            return false;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
