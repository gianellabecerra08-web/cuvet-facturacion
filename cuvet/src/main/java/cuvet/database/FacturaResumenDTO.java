package cuvet.database;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO resumido de factura para consultas de reporte.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class FacturaResumenDTO {
    private int id;
    private String numero;
    private LocalDate fecha;
    private BigDecimal total;
    private String estado;
    public FacturaResumenDTO(int id, String numero, LocalDate fecha, BigDecimal total, String estado) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }
    public int getId() { return id; }
    public String getNumero() { return numero; }
    public LocalDate getFecha() { return fecha; }
    public BigDecimal getTotal() { return total; }
    public String getEstado() { return estado; }
}
