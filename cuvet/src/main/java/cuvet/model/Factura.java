package cuvet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Factura. Calcula subtotal + IGV 18% con BigDecimal.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Factura {
    private static final BigDecimal TASA_IGV = new BigDecimal("0.18");

    private int id;
    private int idAtencion;
    private String numero; // e.g. "F001-00001"
    private LocalDate fecha;
    private List<ItemFactura> items;
    private String estado; // "EMITIDA", "ANULADA"

    public Factura() {
        this.fecha = LocalDate.now();
        this.items = new ArrayList<>();
        this.estado = "EMITIDA";
    }

    public Factura(int idAtencion) {
        this();
        this.idAtencion = idAtencion;
    }

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(ItemFactura::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getIgv() {
        return getSubtotal().multiply(TASA_IGV).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getIgv()).setScale(2, RoundingMode.HALF_UP);
    }

    public void agregarItem(ItemFactura item) { items.add(item); }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public List<ItemFactura> getItems() { return items; }
    public void setItems(List<ItemFactura> items) { this.items = items; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Factura N° " + numero + " | Total: S/ " + getTotal();
    }
}
