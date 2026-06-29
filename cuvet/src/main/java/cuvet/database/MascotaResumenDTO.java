package cuvet.database;

/**
 * DTO resumido de mascota con datos del propietario.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MascotaResumenDTO {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int idCliente;
    private String nombreCliente;
    public MascotaResumenDTO(int id, String nombre, String especie, String raza,
                             int idCliente, String nombreCliente) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public int getIdCliente() { return idCliente; }
    public String getNombreCliente() { return nombreCliente; }
}
