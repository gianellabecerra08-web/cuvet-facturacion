package cuvet.view;

import cuvet.controller.FacturaController;
import cuvet.model.Factura;
import cuvet.util.FormatUtil;
import cuvet.util.NotificacionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Muestra el detalle del comprobante de pago y permite exportar a PDF (RF02, RF08).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class FacturaView extends JPanel {

    private JTable tablaFacturas;
    private DefaultTableModel modelo;
    private JTextArea txtDetalle;
    private JButton btnExportar, btnActualizar;
    private final FacturaController ctrl;

    public FacturaView() {
        this.ctrl = new FacturaController();
        initComponents();
        cargarFacturas();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("🧾 Facturas");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID", "Número", "Fecha", "Subtotal", "IGV", "Total", "Estado"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaFacturas = new JTable(modelo);
        tablaFacturas.getSelectionModel().addListSelectionListener(e -> mostrarDetalle());

        txtDetalle = new JTextArea(12, 40);
        txtDetalle.setEditable(false);
        txtDetalle.setFont(new Font("Courier New", Font.PLAIN, 12));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tablaFacturas), new JScrollPane(txtDetalle));
        split.setDividerLocation(200);
        add(split, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.setBackground(Color.WHITE);
        btnActualizar = new JButton("🔄 Actualizar");
        btnExportar   = new JButton("📄 Exportar Comprobante");
        btnExportar.setBackground(new Color(0, 102, 153)); btnExportar.setForeground(Color.WHITE);
        botones.add(btnActualizar); botones.add(btnExportar);
        add(botones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarFacturas());
        btnExportar.addActionListener(e -> exportar());
    }

    private void cargarFacturas() {
        try {
            modelo.setRowCount(0);
            ctrl.listarTodas().forEach(f -> modelo.addRow(new Object[]{
                    f.getId(), f.getNumero(), FormatUtil.formatearFecha(f.getFecha()),
                    FormatUtil.formatearMonto(f.getSubtotal()), FormatUtil.formatearMonto(f.getIgv()),
                    FormatUtil.formatearMonto(f.getTotal()), f.getEstado()}));
        } catch (Exception e) { txtDetalle.setText("Error al cargar: " + e.getMessage()); }
    }

    private void mostrarDetalle() {
        int fila = tablaFacturas.getSelectedRow();
        if (fila >= 0) {
            try {
                int id = (int) modelo.getValueAt(fila, 0);
                String comprobante = ctrl.exportarPDF(id);
                txtDetalle.setText(comprobante);
            } catch (Exception e) { txtDetalle.setText("Error: " + e.getMessage()); }
        }
    }

    private void exportar() {
        int fila = tablaFacturas.getSelectedRow();
        if (fila < 0) { NotificacionService.mostrarAdvertencia("Seleccione una factura."); return; }
        String texto = txtDetalle.getText();
        JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(texto, 20, 50)),
                "Comprobante", JOptionPane.INFORMATION_MESSAGE);
    }
}
