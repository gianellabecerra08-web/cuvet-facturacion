package cuvet.model;

import java.time.LocalDateTime;

/**
 * Cita médica con máquina de estados.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Cita {

    public enum EstadoCita {
        PENDIENTE, CONFIRMADA, ATENDIDA, CANCELADA
    }

    private int id;
    private int idCliente;
    private int idMascota;
    private int idVeterinario;
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;

    public Cita() { this.estado = EstadoCita.PENDIENTE; }

    public Cita(int idCliente, int idMascota, int idVeterinario,
                LocalDateTime fechaHora, String motivo) {
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = EstadoCita.PENDIENTE;
    }

    public void confirmar()  { this.estado = EstadoCita.CONFIRMADA; }
    public void marcarAtendida() { this.estado = EstadoCita.ATENDIDA; }
    public void cancelar()   { this.estado = EstadoCita.CANCELADA; }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }
}
