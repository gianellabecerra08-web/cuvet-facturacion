package cuvet.view;

import cuvet.controller.ClienteController;
import cuvet.model.Cliente;
import cuvet.util.NotificacionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario de gestión de clientes: alta, búsqueda, edición y eliminación (RF04).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class ClienteView extends JPanel {

    private JTextField txtBuscar, txtNombre, txtApellido, txtDni, txtTelefono, txtEmail;
    private JButton btnBuscar, btnGuardar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private final ClienteController ctrl;

    public ClienteView() {
        this.ctrl = new ClienteController();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("👤 Gestión de Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel de búsqueda
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBackground(Color.WHITE);
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);

        // Formulario
        JPanel form = new JPanel(new GridLayout(6, 2, 8, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        txtNombre   = new JTextField(); txtApellido = new JTextField();
        txtDni      = new JTextField(); txtTelefono = new JTextField();
        txtEmail    = new JTextField();
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Apellido:")); form.add(txtApellido);
        form.add(new JLabel("DNI (8 dígitos):")); form.add(txtDni);
        form.add(new JLabel("Teléfono:")); form.add(txtTelefono);
        form.add(new JLabel("Email:")); form.add(txtEmail);
        btnGuardar = new JButton("Guardar"); btnLimpiar = new JButton("Limpiar");
        btnGuardar.setBackground(new Color(0, 102, 153)); btnGuardar.setForeground(Color.WHITE);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnLimpiar); btnPanel.add(btnGuardar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.setBackground(Color.WHITE);
        norte.add(panelBuscar, BorderLayout.NORTH);
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnPanel, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "DNI", "Teléfono", "Email"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().addListSelectionListener(e -> seleccionarFila());
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscar());
        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private void cargarTabla() {
        try {
            modeloTabla.setRowCount(0);
            ctrl.listarTodos().forEach(c -> modeloTabla.addRow(new Object[]{
                    c.getId(), c.getNombre(), c.getApellido(), c.getDni(), c.getTelefono(), c.getEmail()}));
        } catch (Exception e) {
            NotificacionService.mostrarError("Error al cargar clientes: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            modeloTabla.setRowCount(0);
            ctrl.buscarPorNombre(txtBuscar.getText().trim()).forEach(c -> modeloTabla.addRow(
                    new Object[]{c.getId(), c.getNombre(), c.getApellido(), c.getDni(), c.getTelefono(), c.getEmail()}));
        } catch (Exception e) { NotificacionService.mostrarError(e.getMessage()); }
    }

    private void guardar() {
        try {
            Cliente c = new Cliente(0, txtNombre.getText().trim(), txtApellido.getText().trim(),
                    txtDni.getText().trim(), txtTelefono.getText().trim(), txtEmail.getText().trim());
            ctrl.registrar(c);
            NotificacionService.mostrarInfo("Cliente registrado correctamente.");
            limpiar(); cargarTabla();
        } catch (Exception e) { NotificacionService.mostrarError(e.getMessage()); }
    }

    private void seleccionarFila() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            txtNombre.setText((String) modeloTabla.getValueAt(fila, 1));
            txtApellido.setText((String) modeloTabla.getValueAt(fila, 2));
            txtDni.setText((String) modeloTabla.getValueAt(fila, 3));
            txtTelefono.setText((String) modeloTabla.getValueAt(fila, 4));
            txtEmail.setText((String) modeloTabla.getValueAt(fila, 5));
        }
    }

    private void limpiar() {
        txtNombre.setText(""); txtApellido.setText(""); txtDni.setText("");
        txtTelefono.setText(""); txtEmail.setText(""); txtBuscar.setText("");
    }
}
