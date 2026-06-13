package cuvet.view;

import cuvet.controller.AuthController;
import cuvet.exception.AuthException;
import cuvet.exception.UsuarioInactivoException;
import cuvet.model.Usuario;
import cuvet.util.Constantes;
import cuvet.util.NotificacionService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario de autenticación. Captura usuario y contraseña, invoca AuthController.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JLabel lblIntentos;

    private final AuthController authController;

    public LoginView() {
        this.authController = new AuthController();
        initComponents();
    }

    private void initComponents() {
        setTitle(Constantes.TITULO_APP + " — Iniciar Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("🐾 " + Constantes.NOMBRE_CLINICA, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Usuario
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField(16);
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        // Contraseña
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(16);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Intentos
        lblIntentos = new JLabel(" ");
        lblIntentos.setForeground(Color.RED);
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 11));
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(lblIntentos, gbc);

        // Botón
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(0, 102, 153));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridy = 4;
        panel.add(btnIngresar, gbc);

        // Acción
        btnIngresar.addActionListener(e -> intentarLogin());
        txtPassword.addActionListener(e -> intentarLogin());

        add(panel);
    }

    private void intentarLogin() {
        String username = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            NotificacionService.mostrarAdvertencia("Ingrese usuario y contraseña.");
            return;
        }

        try {
            Usuario usuario = authController.login(username, password);
            dispose();
            new MainView(usuario).setVisible(true);
        } catch (UsuarioInactivoException e) {
            NotificacionService.mostrarError("Usuario inactivo. Contacte al administrador.");
        } catch (AuthException e) {
            String msg = e.getMessage();
            lblIntentos.setText(msg);
            txtPassword.setText("");
            if (authController.estaBloqueado()) {
                btnIngresar.setEnabled(false);
                lblIntentos.setText("Cuenta bloqueada. Contacte al administrador.");
            }
        } catch (Exception e) {
            NotificacionService.mostrarError("Error del sistema: " + e.getMessage());
        }
    }
}
