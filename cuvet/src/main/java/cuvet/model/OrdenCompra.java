package cuvet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Orden de compra para reabastecimiento de stock de medicamentos.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class OrdenCompra {
    private int id;
    private int idProveedor;
    private LocalDate fecha;
    private EstadoOrden estado;
    private List<ItemOrdenCompra> items;

    public enum EstadoOrden {
        PENDIENTE, RECIBIDA
    }

    public OrdenCompra() {
        this.fecha = LocalDate.now();
        this.estado = EstadoOrden.PENDIENTE;
        this.items = new ArrayList<>();
    }

    public OrdenCompra(int idProveedor) {
        this();
        this.idProveedor = idProveedor;
    }

    public void agregarItem(ItemOrdenCompra item) { items.add(item); }

    public BigDecimal getTotal() {
        return items.stream()
                .map(ItemOrdenCompra::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void marcarRecibida() { this.estado = EstadoOrden.RECIBIDA; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public LocalDate getFecha() { return fecha; }
    public EstadoOrden getEstado() { return estado; }
    public List<ItemOrdenCompra> getItems() { return items; }
}