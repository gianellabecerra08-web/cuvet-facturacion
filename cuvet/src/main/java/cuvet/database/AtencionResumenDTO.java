package cuvet.database;

import java.time.LocalDate;

/**
 * DTO resumido de atención para listados.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class AtencionResumenDTO {
    private int id;
    private LocalDate fecha;
    private int idCliente;
    private int idMascota;
    private int idVeterinario;
    private String observaciones;
    public AtencionResumenDTO(int id, LocalDate fecha, int idCliente, int idMascota, int idVeterinario, String observaciones) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.observaciones = observaciones;
    }
    public int getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public int getIdCliente() { return idCliente; }
    public int getIdMascota() { return idMascota; }
    public int getIdVeterinario() { return idVeterinario; }
    public String getObservaciones() { return observaciones; }
}
