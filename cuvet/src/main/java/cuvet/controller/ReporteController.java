package cuvet.controller;

import cuvet.database.FacturaRepository;
import cuvet.exception.ReporteVacioException;
import cuvet.model.Factura;
import cuvet.model.ItemFactura;
import cuvet.model.Reporte;
import cuvet.model.TipoServicio;
import java.math.BigDecimal;
import java.util.List;

/**
 * Genera reportes mensuales de facturación agrupados por TipoServicio.
 * Usa streams y lambdas de Java 17.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class ReporteController {
    private final FacturaRepository facturaRepo;

    public ReporteController() { this.facturaRepo = new FacturaRepository(); }

    /**
     * Genera un Reporte mensual. Usa programación funcional con streams.
     * @throws ReporteVacioException si no hay facturas en el período
     */
    public Reporte generarReporteMensual(int mes, int anio) {
        List<Factura> facturas = facturaRepo.listarPorMes(mes, anio);
        if (facturas.isEmpty()) throw new ReporteVacioException("No hay facturas para el período: " + mes + "/" + anio);

        Reporte reporte = new Reporte(mes, anio);
        reporte.setTotalTransacciones(facturas.size());

        // Programación funcional: streams + merge con BigDecimal::add
        facturas.stream()
                .flatMap(f -> f.getItems().stream())
                .forEach(item -> {
                    TipoServicio tipo = item.getServicio().getTipo();
                    reporte.agregarIngreso(tipo, item.getSubtotal());
                });

        return reporte;
    }

    /** Exporta reporte a CSV */
    public String exportarCSV(Reporte reporte) {
        StringBuilder sb = new StringBuilder("Tipo,Ingreso\n");
        reporte.getIngresosPorTipo().forEach((tipo, monto) ->
                sb.append(tipo.getDescripcion()).append(",").append(monto).append("\n"));
        sb.append("TOTAL,,").append(reporte.getTotalGeneral()).append("\n");
        return sb.toString();
    }
}
