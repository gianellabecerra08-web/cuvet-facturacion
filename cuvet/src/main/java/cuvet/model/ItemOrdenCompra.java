package cuvet.model;

import java.math.BigDecimal;

/**
 * Detalle de un ítem dentro de una orden de compra.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class ItemOrdenCompra {
    private int id;
    private int idOrdenCompra;
    private int idMedicamento;
    private int cantidad;
    private BigDecimal precioCompra;

    public ItemOrdenCompra() {}

    public ItemOrdenCompra(int idMedicamento, int cantidad, BigDecimal precioCompra) {
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    public BigDecimal getSubtotal() {
        return precioCompra.multiply(BigDecimal.valueOf(cantidad));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdOrdenCompra() { return idOrdenCompra; }
    public void setIdOrdenCompra(int idOrdenCompra) { this.idOrdenCompra = idOrdenCompra; }
    public int getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(int idMedicamento) { this.idMedicamento = idMedicamento; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(BigDecimal precioCompra) { this.precioCompra = precioCompra; }
}