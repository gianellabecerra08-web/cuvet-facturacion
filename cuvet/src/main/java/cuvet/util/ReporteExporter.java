package cuvet.util;

import cuvet.model.Reporte;

/**
 * Exporta reportes de facturación a CSV o texto plano.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class ReporteExporter {
    private ReporteExporter() {}

    public static String exportarCSV(Reporte reporte) {
        StringBuilder sb = new StringBuilder();
        sb.append("Mes/Año,").append(reporte.getMes()).append("/").append(reporte.getAnio()).append("\n");
        sb.append("Tipo de Servicio,Ingreso (S/)\n");
        reporte.getIngresosPorTipo().forEach((tipo, monto) ->
                sb.append(tipo.getDescripcion()).append(",").append(monto).append("\n"));
        sb.append("TOTAL GENERAL,").append(reporte.getTotalGeneral()).append("\n");
        sb.append("Transacciones,").append(reporte.getTotalTransacciones()).append("\n");
        return sb.toString();
    }
}
