package cuvet;

import cuvet.database.DatabaseSeeder;
import cuvet.view.LoginView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada principal del Sistema CUVET.
 * Clínica Veterinaria CUVET - Sistema de Gestión y Facturación Integral
 *
 * Curso: Programación Orientada a Objetos II - UTP 2026-I
 * Integrantes:
 *   - Lucero Silva, Freddy Estefano (2312081)    → cuvet.controller
 *   - Becerra Huillcas, Gianella Emely (2411438) → cuvet.model + cuvet.exception
 *   - Cartagena Saco, Jose Alejandro (2310405)   → cuvet.database
 *   - Medina Peña, Danae Alexandra (2211055)     → cuvet.view + cuvet.util
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            DatabaseSeeder.inicializar();
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}
