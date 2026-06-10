package cuvet.model;

import java.util.ArrayList;
import java.util.List;

public class Servicio implements Validable {
    private int id;
    private String descripcion;
    private TipoServicio tipo;
    private double precioBase;
    private boolean activo;

    public Servicio() { this.activo = true; }

    public Servicio(String descripcion, TipoServicio tipo, double precioBase) {
        this();
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    @Override
    public boolean esValido() {
        return descripcion != null && !descripcion.isEmpty() && tipo != null && precioBase >= 0;
    }

    @Override
    public List<String> obtenerErrores() {
        List<String> errores = new ArrayList<>();
        if (descripcion == null || descripcion.isEmpty()) errores.add("La descripción es obligatoria.");
        if (tipo == null) errores.add("El tipo de servicio es obligatorio.");
        if (precioBase < 0) errores.add("El precio no puede ser negativo.");
        return errores;
    }

    @Override
    public void validar() throws Exception {
        List<String> errores = obtenerErrores();
        if (!errores.isEmpty()) throw new Exception(String.join(", ", errores));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public TipoServicio getTipo() { return tipo; }
    public void setTipo(TipoServicio tipo) { this.tipo = tipo; }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double p) { this.precioBase = p; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}