package cuvet.model;

import java.math.BigDecimal;

/**
 * Entidad Medicamento con control de stock mínimo.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Medicamento {
    private int id;
    private String nombre;
    private String principioActivo;
    private String laboratorio;
    private int stockActual;
    private int stockMinimo;
    private BigDecimal precioVenta;
    private boolean activo;

    public Medicamento() { this.activo = true; }

    public Medicamento(int id, String nombre, String principioActivo, String laboratorio,
                       int stockActual, int stockMinimo, BigDecimal precioVenta) {
        this.id = id;
        this.nombre = nombre;
        this.principioActivo = principioActivo;
        this.laboratorio = laboratorio;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.precioVenta = precioVenta;
        this.activo = true;
    }

    /** Retorna true si el stock está en nivel crítico */
    public boolean tieneStockBajo() {
        return stockActual <= stockMinimo;
    }

    public void descontarStock(int cantidad) {
        if (cantidad > stockActual)
            throw new IllegalArgumentException("Stock insuficiente: disponible=" + stockActual + ", solicitado=" + cantidad);
        this.stockActual -= cantidad;
    }

    public void reponerStock(int cantidad) {
        this.stockActual += cantidad;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(String principioActivo) { this.principioActivo = principioActivo; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() { return nombre + " (Stock: " + stockActual + ")"; }
}
