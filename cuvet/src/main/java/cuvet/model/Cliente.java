package cuvet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Cliente (propietario de mascota).
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private List<Mascota> mascotas;

    public Cliente() { this.mascotas = new ArrayList<>(); }

    public Cliente(int id, String nombre, String apellido, String dni, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.mascotas = new ArrayList<>();
    }

    public String getNombreCompleto() { return nombre + " " + apellido; }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
    public void agregarMascota(Mascota m) { this.mascotas.add(m); }

    @Override
    public String toString() { return getNombreCompleto() + " (DNI: " + dni + ")"; }
}
