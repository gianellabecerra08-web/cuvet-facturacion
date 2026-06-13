package cuvet.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Hashing seguro de contraseñas con SHA-256 + salt (Base64).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class PasswordUtil {
    private static final String SALT = "CUVET_2026_SALT";

    private PasswordUtil() {}

    public static String hashear(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String salted = SALT + password;
            byte[] hash = digest.digest(salted.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }
}
