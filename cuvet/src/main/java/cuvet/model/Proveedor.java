package cuvet.model;

/**
 * Laboratorio o distribuidor de medicamentos.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Proveedor {
    private int id;
    private String ruc;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private boolean activo;

    public Proveedor() { this.activo = true; }

    public Proveedor(String ruc, String nombre, String telefono, String email) {
        this();
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() { return nombre + " (RUC: " + ruc + ")"; }
}