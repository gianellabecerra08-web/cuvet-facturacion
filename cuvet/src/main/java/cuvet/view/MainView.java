package cuvet.view;

import cuvet.controller.AuthController;
import cuvet.model.Rol;
import cuvet.model.Usuario;
import cuvet.util.Constantes;
import cuvet.util.NotificacionService;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal. Contiene el menú de navegación y carga los paneles de cada módulo.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class MainView extends JFrame {

    private final Usuario usuarioActual;
    private final AuthController authController;
    private JPanel panelContenido;
    private JLabel lblBienvenida;

    public MainView(Usuario usuario) {
        this.usuarioActual = usuario;
        this.authController = new AuthController();
        initComponents();
    }

    private void initComponents() {
        setTitle(Constantes.TITULO_APP);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Barra superior ---
        JPanel barraTop = new JPanel(new BorderLayout());
        barraTop.setBackground(new Color(0, 102, 153));
        barraTop.setPreferredSize(new Dimension(0, 50));
        JLabel lblTitulo = new JLabel("  🐾 " + Constantes.NOMBRE_CLINICA, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblBienvenida = new JLabel("Bienvenido, " + usuarioActual.getNombre() + " [" + usuarioActual.getRol() + "]   ", SwingConstants.RIGHT);
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 12));
        barraTop.add(lblTitulo, BorderLayout.WEST);
        barraTop.add(lblBienvenida, BorderLayout.EAST);

        // --- Menú lateral ---
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(30, 60, 80));
        menuLateral.setPreferredSize(new Dimension(180, 0));
        menuLateral.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        String[][] opciones = {
            {"🏠 Inicio",     "inicio"},
            {"👤 Clientes",   "clientes"},
            {"🐶 Mascotas",   "mascotas"},
            {"🩺 Atenciones", "atenciones"},
            {"🧾 Facturas",   "facturas"},
            {"📅 Citas",      "citas"},
            {"💊 Medicamentos","medicamentos"},
            {"📊 Reportes",   "reportes"},
            {"🔬 Servicios",  "servicios"},
            {"📋 Historial",  "historial"},
        };

        for (String[] op : opciones) {
            JButton btn = crearBotonMenu(op[0], op[1]);
            menuLateral.add(btn);
            menuLateral.add(Box.createVerticalStrut(4));
        }

        menuLateral.add(Box.createVerticalGlue());
        JButton btnSalir = crearBotonMenu("🚪 Cerrar Sesión", "salir");
        btnSalir.setBackground(new Color(139, 0, 0));
        menuLateral.add(btnSalir);

        // --- Panel de contenido ---
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(Color.WHITE);
        panelContenido.add(crearPanelInicio(), BorderLayout.CENTER);

        add(barraTop, BorderLayout.NORTH);
        add(menuLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    private JButton crearBotonMenu(String texto, String accion) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(170, 38));
        btn.setPreferredSize(new Dimension(170, 38));
        btn.setBackground(new Color(0, 80, 120));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> navegarA(accion));
        return btn;
    }

    private void navegarA(String modulo) {
        panelContenido.removeAll();
        switch (modulo) {
            case "clientes"     -> panelContenido.add(new ClienteView(), BorderLayout.CENTER);
            case "atenciones"   -> panelContenido.add(new AtencionView(), BorderLayout.CENTER);
            case "facturas"     -> panelContenido.add(new FacturaView(), BorderLayout.CENTER);
            case "reportes"     -> panelContenido.add(new ReporteView(), BorderLayout.CENTER);
            case "servicios"    -> panelContenido.add(new ServicioView(), BorderLayout.CENTER);
            case "medicamentos" -> panelContenido.add(new MedicamentoView(), BorderLayout.CENTER);
            case "citas"        -> panelContenido.add(new CitaView(), BorderLayout.CENTER);
            case "historial"    -> panelContenido.add(new HistorialView(), BorderLayout.CENTER);
            case "salir"        -> {
                if (NotificacionService.confirmar("¿Desea cerrar la sesión?")) {
                    authController.logout();
                    dispose();
                    new LoginView().setVisible(true);
                }
                return;
            }
            default             -> panelContenido.add(crearPanelInicio(), BorderLayout.CENTER);
        }
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private JPanel crearPanelInicio() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel("<html><center><h1>🐾 Bienvenido a CUVET</h1>"
                + "<p>Sistema de Gestión y Facturación Integral</p>"
                + "<p>Use el menú lateral para navegar.</p></center></html>", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        p.add(lbl);
        return p;
    }
}
