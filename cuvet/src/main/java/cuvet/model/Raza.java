package cuvet.model;

/**
 * Catálogo de razas por especie, para estandarizar el registro de mascotas.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Raza {
    private int id;
    private String nombre;
    private String especie; // "PERRO", "GATO", "AVE", "OTRO"

    public Raza() {}

    public Raza(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    @Override
    public String toString() { return nombre; }
}