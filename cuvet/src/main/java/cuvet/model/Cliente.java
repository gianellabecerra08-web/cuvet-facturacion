package cuvet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Auditable, Validable {
    private int id;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private TipoDocumento tipoDocumento;
    private String telefono;
    private String email;
    private Direccion direccion;
    private List<Mascota> mascotas;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;

    public Cliente() {
        this.mascotas = new ArrayList<>();
        this.activo = true;
    }

    public Cliente(String nombre, String apellido, String numeroDocumento, TipoDocumento tipoDocumento, String telefono) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.telefono = telefono;
    }

    public String getNombreCompleto() { return nombre + " " + apellido; }

    public void agregarMascota(Mascota mascota) {
        if (mascota != null && !mascotas.contains(mascota)) {
            mascotas.add(mascota);
            mascota.setIdCliente(this.id);
        }
    }

    @Override
    public boolean esValido() {
        return nombre != null && !nombre.isEmpty()
                && apellido != null && !apellido.isEmpty()
                && numeroDocumento != null && !numeroDocumento.isEmpty()
                && telefono != null && !telefono.isEmpty();
    }

    @Override
    public List<String> obtenerErrores() {
        List<String> errores = new ArrayList<>();
        if (nombre == null || nombre.isEmpty()) errores.add("El nombre es obligatorio.");
        if (apellido == null || apellido.isEmpty()) errores.add("El apellido es obligatorio.");
        if (numeroDocumento == null || numeroDocumento.isEmpty()) errores.add("El número de documento es obligatorio.");
        if (telefono == null || telefono.isEmpty()) errores.add("El teléfono es obligatorio.");
        return errores;
    }

    @Override
    public void validar() throws Exception {
        List<String> errores = obtenerErrores();
        if (!errores.isEmpty()) throw new Exception(String.join(", ", errores));
    }

    @Override public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    @Override public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    @Override public String getUsuarioCreacion() { return usuarioCreacion; }
    @Override public void setFechaCreacion(LocalDateTime f) { this.fechaCreacion = f; }
    @Override public void setFechaModificacion(LocalDateTime f) { this.fechaModificacion = f; }
    @Override public void setUsuarioCreacion(String u) { this.usuarioCreacion = u; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String n) { this.numeroDocumento = n; }
    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(TipoDocumento t) { this.tipoDocumento = t; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public List<Mascota> getMascotas() { return mascotas; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}