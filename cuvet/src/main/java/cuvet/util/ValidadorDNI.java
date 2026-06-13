package cuvet.util;

/**
 * Valida el formato del DNI peruano (8 dígitos numéricos).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class ValidadorDNI {
    private ValidadorDNI() {}

    public static void validar(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("DNI inválido: debe tener exactamente 8 dígitos numéricos. Recibido: '" + dni + "'");
        }
    }

    public static boolean esValido(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }
}
