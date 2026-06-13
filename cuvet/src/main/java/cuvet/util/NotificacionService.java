package cuvet.util;

import javax.swing.JOptionPane;

/**
 * Centraliza notificaciones Swing (JOptionPane): error, info, advertencia, confirmación.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class NotificacionService {
    private NotificacionService() {}

    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(String mensaje) {
        int resultado = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return resultado == JOptionPane.YES_OPTION;
    }
}
