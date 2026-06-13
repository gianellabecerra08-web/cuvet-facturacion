package cuvet.model;

import java.math.BigDecimal;

/**
 * Entidad Servicio (ítem del catálogo facturable).
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Servicio {
    private int id;
    private TipoServicio tipo;
    private String descripcion;
    private BigDecimal precio;
    private boolean activo;

    public Servicio() { this.activo = true; }

    public Servicio(int id, TipoServicio tipo, String descripcion, BigDecimal precio) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = true;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public TipoServicio getTipo() { return tipo; }
    public void setTipo(TipoServicio tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() { return "[" + tipo.name() + "] " + descripcion + " - S/ " + precio; }
}
