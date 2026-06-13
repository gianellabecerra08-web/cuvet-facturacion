package cuvet.view;

import cuvet.controller.ReporteController;
import cuvet.exception.ReporteVacioException;
import cuvet.model.Reporte;
import cuvet.util.FormatUtil;
import cuvet.util.NotificacionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

/**
 * Permite filtrar y visualizar reportes mensuales de ingresos por tipo de servicio (RF06).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class ReporteView extends JPanel {

    private JComboBox<String> cmbMes;
    private JSpinner spnAnio;
    private JTable tablaReporte;
    private DefaultTableModel modelo;
    private JLabel lblTotal;
    private final ReporteController ctrl;

    public ReporteView() {
        this.ctrl = new ReporteController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("📊 Reportes de Facturación");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filtros.setBackground(Color.WHITE);
        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio",
                          "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        cmbMes = new JComboBox<>(meses);
        cmbMes.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        spnAnio = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 2020, 2100, 1));
        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBackground(new Color(0, 102, 153)); btnGenerar.setForeground(Color.WHITE);
        filtros.add(new JLabel("Mes:")); filtros.add(cmbMes);
        filtros.add(new JLabel("Año:")); filtros.add(spnAnio);
        filtros.add(btnGenerar);
        add(filtros, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"Tipo de Servicio", "Ingresos (S/)"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaReporte = new JTable(modelo);
        tablaReporte.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaReporte.setRowHeight(28);
        add(new JScrollPane(tablaReporte), BorderLayout.CENTER);

        lblTotal = new JLabel("Total General: S/ 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(lblTotal, BorderLayout.SOUTH);

        btnGenerar.addActionListener(e -> generarReporte());
    }

    private void generarReporte() {
        int mes  = cmbMes.getSelectedIndex() + 1;
        int anio = (int) spnAnio.getValue();
        try {
            Reporte r = ctrl.generarReporteMensual(mes, anio);
            modelo.setRowCount(0);
            r.getIngresosPorTipo().forEach((tipo, monto) ->
                    modelo.addRow(new Object[]{tipo.getDescripcion(), FormatUtil.formatearMonto(monto)}));
            lblTotal.setText("Total General: " + FormatUtil.formatearMonto(r.getTotalGeneral())
                    + "   |   Transacciones: " + r.getTotalTransacciones());
        } catch (ReporteVacioException e) {
            modelo.setRowCount(0);
            lblTotal.setText("Sin datos para el período seleccionado.");
        } catch (Exception e) {
            NotificacionService.mostrarError("Error al generar reporte: " + e.getMessage());
        }
    }
}
