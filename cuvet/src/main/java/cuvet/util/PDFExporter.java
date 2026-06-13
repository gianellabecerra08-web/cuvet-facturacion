package cuvet.util;

import cuvet.model.Factura;
import cuvet.model.ItemFactura;
import java.time.format.DateTimeFormatter;

/**
 * Genera comprobantes de pago en formato texto a partir del objeto Factura.
 * En producción se integraría con una librería PDF (iText/Apache PDFBox).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public final class PDFExporter {

    private PDFExporter() {}

    public static String exportar(Factura factura) {
        StringBuilder sb = new StringBuilder();
        String linea = "=".repeat(50);
        sb.append(linea).append("\n");
        sb.append("       CLINICA VETERINARIA CUVET\n");
        sb.append("       RUC: 20123456789\n");
        sb.append("       Av. Veterinaria 123, Lima\n");
        sb.append(linea).append("\n");
        sb.append("FACTURA: ").append(factura.getNumero()).append("\n");
        sb.append("Fecha:   ").append(factura.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
        sb.append("-".repeat(50)).append("\n");
        sb.append(String.format("%-30s %8s %10s\n", "DESCRIPCIÓN", "CANT.", "SUBTOTAL"));
        sb.append("-".repeat(50)).append("\n");
        for (ItemFactura item : factura.getItems()) {
            sb.append(String.format("%-30s %8d %10s\n",
                    item.getServicio().getDescripcion(),
                    item.getCantidad(),
                    FormatUtil.formatearMonto(item.getSubtotal())));
        }
        sb.append("-".repeat(50)).append("\n");
        sb.append(String.format("%-40s %10s\n", "Subtotal:", FormatUtil.formatearMonto(factura.getSubtotal())));
        sb.append(String.format("%-40s %10s\n", "IGV (18%):", FormatUtil.formatearMonto(factura.getIgv())));
        sb.append(String.format("%-40s %10s\n", "TOTAL:", FormatUtil.formatearMonto(factura.getTotal())));
        sb.append(linea).append("\n");
        sb.append("       ¡Gracias por confiar en CUVET!\n");
        sb.append(linea).append("\n");
        return sb.toString();
    }
}
