package cuvet.controller;

import cuvet.database.FacturaRepository;
import cuvet.exception.FacturaNotFoundException;
import cuvet.model.Atencion;
import cuvet.model.Factura;
import cuvet.model.ItemFactura;
import cuvet.model.Servicio;
import cuvet.util.AuditoriaLogger;
import cuvet.util.PDFExporter;
import cuvet.util.SessionManager;

import java.util.List;

/**
 * Genera facturas a partir de atenciones. Calcula subtotal + IGV 18%.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class FacturaController {

    private final FacturaRepository facturaRepo;
    private final AuditoriaLogger auditoria;

    public FacturaController() {
        this.facturaRepo = new FacturaRepository();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    /**
     * Genera y persiste la factura para una atención.
     * Cálculo de IGV 18% mediante BigDecimal en el modelo Factura.
     *
     * @param atencion Atención ya persistida con sus servicios
     * @return Factura generada con número, ítems y totales
     */
    public Factura generarFactura(Atencion atencion) {
        Factura factura = new Factura(atencion.getId());
        factura.setNumero(facturaRepo.generarNumeroFactura());

        // Construir ítems de factura a partir de los servicios de la atención
        for (Servicio s : atencion.getServicios()) {
            ItemFactura item = new ItemFactura(s, 1);
            factura.agregarItem(item);
        }

        facturaRepo.guardar(factura);

        int idUsuario = SessionManager.getInstancia().getUsuarioActual() != null
                ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(idUsuario, "INSERT", "facturas", factura.getId(),
                "Factura generada: " + factura.getNumero() + " | Total: S/ " + factura.getTotal());

        return factura;
    }

    public Factura buscarPorId(int id) {
        return facturaRepo.buscarPorId(id)
                .orElseThrow(() -> new FacturaNotFoundException(String.valueOf(id)));
    }

    public List<Factura> listarPorMes(int mes, int anio) {
        return facturaRepo.listarPorMes(mes, anio);
    }

    public List<Factura> listarTodas() {
        return facturaRepo.listarTodos();
    }

    /** Exporta la factura a texto/PDF */
    public String exportarPDF(int idFactura) {
        Factura factura = buscarPorId(idFactura);
        return PDFExporter.exportar(factura);
    }

    public void anularFactura(int idFactura) {
        Factura factura = buscarPorId(idFactura);
        factura.setEstado("ANULADA");
        facturaRepo.actualizar(factura);

        int idUsuario = SessionManager.getInstancia().getUsuarioActual() != null
                ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(idUsuario, "UPDATE", "facturas", idFactura, "Factura anulada: " + factura.getNumero());
    }
}
