package cuvet.view;

import cuvet.controller.MascotaController;
import cuvet.model.Mascota;
import cuvet.util.NotificacionService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario de gestión de clientes: alta, búsqueda, edición y eliminación (RF04).
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MascotaView extends JPanel {

    private JTextField txtBuscar, txtNombre, txtEspecie, txtRaza, txtEdad, txtSexo, txtClienteId;
    private JButton btnBuscar, btnGuardar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private final MascotaController ctrl;

    public MascotaView() {
        this.ctrl = new MascotaController();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("🐾 Gestión de Mascotas");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBackground(Color.WHITE);
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);

        JPanel form = new JPanel(new GridLayout(6, 2, 8, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Datos del Mascota"));

        txtNombre = new JTextField();
        txtEspecie = new JTextField();
        txtRaza = new JTextField();
        txtEdad = new JTextField();
        txtSexo = new JTextField();
        txtClienteId = new JTextField();

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Especie:")); form.add(txtEspecie);
        form.add(new JLabel("Raza:")); form.add(txtRaza);
        form.add(new JLabel("Edad (meses):")); form.add(txtEdad);
        form.add(new JLabel("Sexo (M/H):")); form.add(txtSexo);
        form.add(new JLabel("ID Cliente:")); form.add(txtClienteId);

        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnGuardar.setBackground(new Color(0, 102, 153));
        btnGuardar.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnLimpiar);
        btnPanel.add(btnGuardar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.setBackground(Color.WHITE);
        norte.add(panelBuscar, BorderLayout.NORTH);
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnPanel, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Especie", "Raza", "Sexo", "Edad", "ID Cliente"}, 0) {
            @Override
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
            ctrl.listarTodos().forEach(m -> modeloTabla.addRow(new Object[]{
                    m.getId(), m.getNombre(), m.getEspecie(), m.getRaza(), m.getSexo(), m.getEdadMeses(), m.getIdCliente()
            }));
        } catch (Exception e) {
            NotificacionService.mostrarError("Error al cargar mascotas: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            modeloTabla.setRowCount(0);
            ctrl.buscarPorNombre(txtBuscar.getText().trim()).forEach(m -> modeloTabla.addRow(new Object[]{
                    m.getId(), m.getNombre(), m.getEspecie(), m.getRaza(), m.getSexo(), m.getEdadMeses(), m.getIdCliente()
            }));
        } catch (Exception e) {
            NotificacionService.mostrarError(e.getMessage());
        }
    }

    private void guardar() {
        try {
            Mascota m = new Mascota();
            m.setId(0); // Ajusta según tu lógica de auto-incremento
            m.setNombre(txtNombre.getText().trim());
            m.setEspecie(txtEspecie.getText().trim());
            m.setRaza(txtRaza.getText().trim());
            m.setEdadMeses(Integer.parseInt(txtEdad.getText().trim()));
            m.setSexo(txtSexo.getText().trim());
            m.setIdCliente(Integer.parseInt(txtClienteId.getText().trim()));

            ctrl.registrar(m);
            NotificacionService.mostrarInfo("Mascota registrada correctamente.");
            limpiar();
            cargarTabla();
        } catch (Exception e) {
            NotificacionService.mostrarError("Error al guardar mascota: " + e.getMessage());
        }
    }

    private void seleccionarFila() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            txtNombre.setText(String.valueOf(modeloTabla.getValueAt(fila, 1)));
            txtEspecie.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
            txtRaza.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
            txtSexo.setText(String.valueOf(modeloTabla.getValueAt(fila, 4)));
            txtEdad.setText(String.valueOf(modeloTabla.getValueAt(fila, 5)));
            txtClienteId.setText(String.valueOf(modeloTabla.getValueAt(fila, 6)));
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtEspecie.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        txtSexo.setText("");
        txtClienteId.setText("");
        txtBuscar.setText("");
    }
}