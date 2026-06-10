package cuvet.model;

import java.time.LocalDateTime;

public class ItemReporte {
    private String descripcion;
    private double monto;
    private int cantidad;
    private LocalDateTime fecha;
    private String categoria;

    public ItemReporte() {}

    public ItemReporte(String descripcion, double monto, int cantidad, String categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.fecha = LocalDateTime.now();
    }

    public double getMontoTotal() { return monto * cantidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}