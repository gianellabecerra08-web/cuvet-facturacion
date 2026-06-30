package cuvet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Control diario de apertura y cierre de caja por usuario.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class CierreCaja {
    private int id;
    private int idUsuario;
    private LocalDate fecha;
    private BigDecimal montoApertura;
    private BigDecimal montoCierreCalculado;
    private BigDecimal montoCierreReal;
    private boolean cerrado;

    public CierreCaja() {
        this.fecha = LocalDate.now();
        this.cerrado = false;
    }

    public CierreCaja(int idUsuario, BigDecimal montoApertura) {
        this();
        this.idUsuario = idUsuario;
        this.montoApertura = montoApertura;
    }

    public BigDecimal getDescuadre() {
        if (montoCierreCalculado == null || montoCierreReal == null) return BigDecimal.ZERO;
        return montoCierreReal.subtract(montoCierreCalculado);
    }

    public void cerrar(BigDecimal montoCalculado, BigDecimal montoReal) {
        this.montoCierreCalculado = montoCalculado;
        this.montoCierreReal = montoReal;
        this.cerrado = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public BigDecimal getMontoApertura() { return montoApertura; }
    public void setMontoApertura(BigDecimal montoApertura) { this.montoApertura = montoApertura; }
    public BigDecimal getMontoCierreCalculado() { return montoCierreCalculado; }
    public BigDecimal getMontoCierreReal() { return montoCierreReal; }
    public boolean isCerrado() { return cerrado; }
}