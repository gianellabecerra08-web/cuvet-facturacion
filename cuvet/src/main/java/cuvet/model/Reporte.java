package cuvet.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Agregado de ingresos por TipoServicio para un período.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Reporte {
    private int mes;
    private int anio;
    private Map<TipoServicio, BigDecimal> ingresosPorTipo;
    private int totalTransacciones;

    public Reporte(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
        this.ingresosPorTipo = new HashMap<>();
    }

    /** Programación funcional: merge con BigDecimal::add */
    public void agregarIngreso(TipoServicio tipo, BigDecimal monto) {
        ingresosPorTipo.merge(tipo, monto, BigDecimal::add);
    }

    public BigDecimal getTotalGeneral() {
        return ingresosPorTipo.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters y Setters
    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public Map<TipoServicio, BigDecimal> getIngresosPorTipo() { return ingresosPorTipo; }
    public void setIngresosPorTipo(Map<TipoServicio, BigDecimal> ingresosPorTipo) { this.ingresosPorTipo = ingresosPorTipo; }
    public int getTotalTransacciones() { return totalTransacciones; }
    public void setTotalTransacciones(int totalTransacciones) { this.totalTransacciones = totalTransacciones; }
}
