package cuvet.util;

import java.util.regex.Pattern;

/**
 * Valida correos electrónicos con expresión regular.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class ValidadorEmail {
    private static final Pattern PATRON = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private ValidadorEmail() {}

    public static boolean esValido(String email) {
        return email != null && PATRON.matcher(email).matches();
    }

    public static void validar(String email) {
        if (!esValido(email)) throw new IllegalArgumentException("Email inválido: " + email);
    }
}
