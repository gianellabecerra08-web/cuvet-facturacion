package cuvet.model;

/**
 * Entidad Mascota.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edadMeses;
    private String sexo; // "M" o "F"
    private int idCliente;

    public Mascota() {}

    public Mascota(int id, String nombre, String especie, String raza, int edadMeses, int idCliente) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edadMeses = edadMeses;
        this.idCliente = idCliente;
    }

    public String getEdadFormateada() {
        if (edadMeses < 12) return edadMeses + " mes(es)";
        int years = edadMeses / 12;
        int months = edadMeses % 12;
        return years + " año(s)" + (months > 0 ? " " + months + " mes(es)" : "");
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public int getEdadMeses() { return edadMeses; }
    public void setEdadMeses(int edadMeses) { this.edadMeses = edadMeses; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    @Override
    public String toString() { return nombre + " (" + especie + " - " + raza + ")"; }
}
