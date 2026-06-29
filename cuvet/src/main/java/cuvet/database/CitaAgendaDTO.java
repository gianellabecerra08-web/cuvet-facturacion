package cuvet.database;

import java.time.LocalDateTime;

/**
 * DTO de cita para la agenda diaria.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class CitaAgendaDTO {
    private int id;
    private int idCliente;
    private int idMascota;
    private int idVeterinario;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
    public CitaAgendaDTO(int id, int idCliente, int idMascota, int idVeterinario,
                         LocalDateTime fechaHora, String motivo, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
    }
    public int getId() { return id; }
    public int getIdCliente() { return idCliente; }
    public int getIdMascota() { return idMascota; }
    public int getIdVeterinario() { return idVeterinario; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public String getEstado() { return estado; }
}
