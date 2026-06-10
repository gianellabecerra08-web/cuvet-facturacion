package cuvet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Calculable, Auditable, Exportable {
    private static final double IGV = 0.18;
    private int id;
    private int idAtencion;
    private int idCliente;
    private String serie;
    private int numero;
    private LocalDateTime fechaEmision;
    private EstadoFactura estado;
    private List<ItemFactura> items;
    private MetodoPago metodoPago;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;

    public Factura() {
        this.items = new ArrayList<>();
        this.estado = EstadoFactura.PENDIENTE;
        this.fechaEmision = LocalDateTime.now();
    }

    public void agregarItem(ItemFactura item) { if (item != null) items.add(item); }

    @Override public double calcularSubtotal() {
        return items.stream().mapToDouble(ItemFactura::getSubtotal).sum();
    }
    @Override public double calcularIGV() { return calcularSubtotal() * IGV; }
    @Override public double calcularTotal() { return calcularSubtotal() + calcularIGV(); }
    @Override public byte[] exportarPDF() { return new byte[0]; }
    @Override public String exportarCSV() {
        StringBuilder sb = new StringBuilder("Descripcion,Cantidad,PrecioUnit,Subtotal\n");
        for (ItemFactura item : items)
            sb.append(item.getDescripcion()).append(",").append(item.getCantidad()).append(",")
                    .append(item.getPrecioUnitario()).append(",").append(item.getSubtotal()).append("\n");
        return sb.toString();
    }
    @Override public String getNombreArchivo() { return "Factura_" + serie + "-" + numero + ".pdf"; }

    @Override public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    @Override public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    @Override public String getUsuarioCreacion() { return usuarioCreacion; }
    @Override public void setFechaCreacion(LocalDateTime f) { this.fechaCreacion = f; }
    @Override public void setFechaModificacion(LocalDateTime f) { this.fechaModificacion = f; }
    @Override public void setUsuarioCreacion(String u) { this.usuarioCreacion = u; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int id) { this.idAtencion = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int id) { this.idCliente = id; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime f) { this.fechaEmision = f; }
    public EstadoFactura getEstado() { return estado; }
    public void setEstado(EstadoFactura estado) { this.estado = estado; }
    public List<ItemFactura> getItems() { return items; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago m) { this.metodoPago = m; }
}