package cuvet.database;

import cuvet.model.TipoServicio;
import java.math.BigDecimal;

/**
 * DTO de ingresos mensuales agrupados por tipo de servicio.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ReporteMensualDTO {
    private TipoServicio tipo;
    private BigDecimal total;
    public ReporteMensualDTO(TipoServicio tipo, BigDecimal total) {
        this.tipo = tipo;
        this.total = total;
    }
    public TipoServicio getTipo() { return tipo; }
    public BigDecimal getTotal() { return total; }
}
