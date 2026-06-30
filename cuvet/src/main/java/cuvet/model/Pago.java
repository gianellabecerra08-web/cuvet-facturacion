package cuvet.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Registro del pago efectuado sobre una factura.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Pago {
    private int id;
    private int idFactura;
    private MetodoPago metodoPago;
    private BigDecimal montoPagado;
    private String transaccionReferencia;
    private LocalDateTime fechaPago;

    public enum MetodoPago {
        EFECTIVO, TARJETA, YAPE, PLIN, TRANSFERENCIA
    }

    public Pago() { this.fechaPago = LocalDateTime.now(); }

    public Pago(int idFactura, MetodoPago metodoPago, BigDecimal montoPagado) {
        this.idFactura = idFactura;
        this.metodoPago = metodoPago;
        this.montoPagado = montoPagado;
        this.fechaPago = LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getMontoPagado() { return montoPagado; }
    public void setMontoPagado(BigDecimal montoPagado) { this.montoPagado = montoPagado; }
    public String getTransaccionReferencia() { return transaccionReferencia; }
    public void setTransaccionReferencia(String t) { this.transaccionReferencia = t; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    @Override
    public String toString() { return metodoPago + " - S/ " + montoPagado; }
}