package cuvet.model;

/**
 * Entidad Usuario del sistema con control de acceso por roles.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Usuario {
    private int id;
    private String username;
    private String passwordHash; // SHA-256 + salt
    private String nombre;
    private Rol rol;
    private boolean activo;

    public Usuario() { this.activo = true; }

    public Usuario(int id, String username, String passwordHash, String nombre, Rol rol) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = true;
    }

    public boolean tienePermiso(Rol rolRequerido) {
        if (this.rol == Rol.ADMINISTRADOR) return true;
        return this.rol == rolRequerido;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() { return nombre + " [" + rol + "]"; }
}
