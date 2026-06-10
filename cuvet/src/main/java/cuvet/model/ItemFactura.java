package cuvet.model;

public class ItemFactura implements Calculable {
    private int id;
    private int idFactura;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private TipoServicio tipoServicio;

    public ItemFactura() {}

    public ItemFactura(String descripcion, int cantidad, double precioUnitario, TipoServicio tipoServicio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.tipoServicio = tipoServicio;
    }

    public double getSubtotal() { return cantidad * precioUnitario; }

    @Override public double calcularSubtotal() { return getSubtotal(); }
    @Override public double calcularIGV() { return getSubtotal() * 0.18; }
    @Override public double calcularTotal() { return getSubtotal() + calcularIGV(); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int id) { this.idFactura = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double p) { this.precioUnitario = p; }
    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio t) { this.tipoServicio = t; }
}