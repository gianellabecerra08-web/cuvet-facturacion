package cuvet.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Formatea fechas, montos y textos para la interfaz gráfica.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class FormatUtil {
    private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private FormatUtil() {}

    public static String formatearFecha(LocalDate fecha) {
        return fecha != null ? fecha.format(FMT_FECHA) : "";
    }

    public static String formatearFechaHora(LocalDateTime dt) {
        return dt != null ? dt.format(FMT_DATETIME) : "";
    }

    public static String formatearMonto(BigDecimal monto) {
        return monto != null ? "S/ " + String.format("%.2f", monto) : "S/ 0.00";
    }

    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1).toLowerCase();
    }
}
