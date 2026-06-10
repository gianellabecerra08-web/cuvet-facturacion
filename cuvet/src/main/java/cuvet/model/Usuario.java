package cuvet.model;

import java.time.LocalDateTime;

public class Usuario implements Auditable {
    private int id;
    private String username;
    private String passwordHash;
    private String nombre;
    private String apellido;
    private Rol rol;
    private boolean activo;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;

    public Usuario() { this.activo = true; }

    public Usuario(String username, String passwordHash, String nombre, String apellido, Rol rol) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }

    public String getNombreCompleto() { return nombre + " " + apellido; }

    public boolean tienePermiso(String permiso) {
        if (rol == null || rol.getPermisos() == null) return false;
        return rol.getPermisos().stream().anyMatch(p -> p.getCodigo().equals(permiso));
    }

    @Override public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    @Override public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    @Override public String getUsuarioCreacion() { return usuarioCreacion; }
    @Override public void setFechaCreacion(LocalDateTime f) { this.fechaCreacion = f; }
    @Override public void setFechaModificacion(LocalDateTime f) { this.fechaModificacion = f; }
    @Override public void setUsuarioCreacion(String u) { this.usuarioCreacion = u; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String p) { this.passwordHash = p; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(LocalDateTime u) { this.ultimoAcceso = u; }
}