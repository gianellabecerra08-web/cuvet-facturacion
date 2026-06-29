package cuvet.database;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de ingresos agrupados por día.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class IngresoDiarioDTO {
    private LocalDate fecha;
    private BigDecimal total;
    public IngresoDiarioDTO(LocalDate fecha, BigDecimal total) {
        this.fecha = fecha;
        this.total = total;
    }
    public LocalDate getFecha() { return fecha; }
    public BigDecimal getTotal() { return total; }
}
