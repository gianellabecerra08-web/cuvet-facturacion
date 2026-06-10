package cuvet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mascota implements Auditable {
    private int id;
    private String nombre;
    private Especie especie;
    private Raza raza;
    private LocalDate fechaNacimiento;
    private String sexo;
    private double peso;
    private String color;
    private int idCliente;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;

    public Mascota() { this.activo = true; }

    public Mascota(String nombre, Especie especie, Raza raza, int idCliente) {
        this();
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.idCliente = idCliente;
    }

    public int getEdadAnios() {
        if (fechaNacimiento == null) return 0;
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
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
    public Especie getEspecie() { return especie; }
    public void setEspecie(Especie especie) { this.especie = especie; }
    public Raza getRaza() { return raza; }
    public void setRaza(Raza raza) { this.raza = raza; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}