package cuvet.model;

import java.math.BigDecimal;

/**
 * Línea individual de una factura.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class ItemFactura {
    private int id;
    private int idFactura;
    private Servicio servicio;
    private int cantidad;
    private BigDecimal precioUnitario;

    public ItemFactura() {}

    public ItemFactura(Servicio servicio, int cantidad) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precioUnitario = servicio.getPrecio();
    }

    public BigDecimal getSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }
    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
