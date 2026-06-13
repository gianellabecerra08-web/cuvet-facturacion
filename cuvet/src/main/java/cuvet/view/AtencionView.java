package cuvet.view;

import cuvet.controller.AtencionController;
import cuvet.controller.ClienteController;
import cuvet.controller.ServicioController;
import cuvet.exception.DuplicadoException;
import cuvet.model.*;
import cuvet.util.NotificacionService;
import cuvet.util.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Formulario para registrar los servicios prestados a una mascota.
 * Muestra alertas de duplicado cuando corresponde (RF01, RF03).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class AtencionView extends JPanel {

    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Mascota> cmbMascota;
    private JList<Servicio> lstServicios;
    private JTextArea txtObservaciones;
    private JButton btnRegistrar;
    private JLabel lblResultado;

    private final AtencionController atencionCtrl;
    private final ClienteController clienteCtrl;
    private final ServicioController servicioCtrl;

    public AtencionView() {
        this.atencionCtrl = new AtencionController();
        this.clienteCtrl  = new ClienteController();
        this.servicioCtrl = new ServicioController();
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("🩺 Registrar Atención");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;

        cmbCliente  = new JComboBox<>();
        cmbMascota  = new JComboBox<>();
        lstServicios = new JList<>();
        lstServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstServicios.setVisibleRowCount(6);
        txtObservaciones = new JTextArea(3, 30);
        btnRegistrar = new JButton("Registrar Atención y Generar Factura");
        btnRegistrar.setBackground(new Color(0, 102, 153));
        btnRegistrar.setForeground(Color.WHITE);
        lblResultado = new JLabel(" ");

        cmbCliente.addActionListener(e -> cargarMascotas());

        g.gridx = 0; g.gridy = 0; g.weightx = 0; form.add(new JLabel("Cliente:"), g);
        g.gridx = 1; g.weightx = 1; form.add(cmbCliente, g);
        g.gridx = 0; g.gridy = 1; g.weightx = 0; form.add(new JLabel("Mascota:"), g);
        g.gridx = 1; g.weightx = 1; form.add(cmbMascota, g);
        g.gridx = 0; g.gridy = 2; g.weightx = 0; form.add(new JLabel("Servicios:"), g);
        g.gridx = 1; g.weightx = 1; form.add(new JScrollPane(lstServicios), g);
        g.gridx = 0; g.gridy = 3; g.weightx = 0; form.add(new JLabel("Observaciones:"), g);
        g.gridx = 1; g.weightx = 1; form.add(new JScrollPane(txtObservaciones), g);
        g.gridx = 0; g.gridy = 4; g.gridwidth = 2; form.add(btnRegistrar, g);
        g.gridy = 5; form.add(lblResultado, g);

        add(form, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrar());
    }

    private void cargarDatos() {
        try {
            clienteCtrl.listarTodos().forEach(c -> cmbCliente.addItem(c));
            servicioCtrl.listarActivos().forEach(s -> {
                DefaultListModel<Servicio> model = (DefaultListModel<Servicio>) lstServicios.getModel();
                if (model == null) { model = new DefaultListModel<>(); lstServicios.setModel(model); }
                model.addElement(s);
            });
        } catch (Exception e) {
            lblResultado.setText("Error al cargar datos: " + e.getMessage());
        }
    }

    private void cargarMascotas() {
        cmbMascota.removeAllItems();
        Cliente c = (Cliente) cmbCliente.getSelectedItem();
        if (c != null) {
            clienteCtrl.listarMascotas(c.getId()).forEach(m -> cmbMascota.addItem(m));
        }
    }

    private void registrar() {
        Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
        Mascota mascota = (Mascota) cmbMascota.getSelectedItem();
        List<Servicio> seleccionados = lstServicios.getSelectedValuesList();

        if (cliente == null || mascota == null || seleccionados.isEmpty()) {
            NotificacionService.mostrarAdvertencia("Seleccione cliente, mascota y al menos un servicio.");
            return;
        }

        List<Integer> idsServicios = new ArrayList<>();
        seleccionados.forEach(s -> idsServicios.add(s.getId()));

        int idVet = SessionManager.getInstancia().getUsuarioActual() != null
                ? SessionManager.getInstancia().getUsuarioActual().getId() : 1;

        try {
            Atencion atencion = atencionCtrl.registrarAtencion(
                    cliente.getId(), mascota.getId(), idVet, idsServicios, txtObservaciones.getText());
            lblResultado.setForeground(new Color(0, 128, 0));
            lblResultado.setText("✔ Atención registrada. Factura generada para " + mascota.getNombre());
            NotificacionService.mostrarInfo("Atención registrada correctamente.\nSe ha generado la factura.");
        } catch (DuplicadoException e) {
            lblResultado.setForeground(Color.RED);
            lblResultado.setText("⚠ " + e.getMessage());
            NotificacionService.mostrarAdvertencia("⚠ Duplicado detectado:\n" + e.getMessage());
        } catch (Exception e) {
            NotificacionService.mostrarError("Error: " + e.getMessage());
        }
    }
}
