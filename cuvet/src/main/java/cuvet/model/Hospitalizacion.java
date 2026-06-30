package cuvet.model;

import java.time.LocalDate;

/**
 * Registro de internamiento de una mascota.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Hospitalizacion {
    private int id;
    private int idMascota;
    private int idVeterinario;
    private int idCanil;
    private LocalDate fechaIngreso;
    private LocalDate fechaAlta;
    private String motivo;
    private EstadoHospitalizacion estado;

    public enum EstadoHospitalizacion {
        EN_OBSERVACION, ALTA
    }

    public Hospitalizacion() {
        this.fechaIngreso = LocalDate.now();
        this.estado = EstadoHospitalizacion.EN_OBSERVACION;
    }

    public Hospitalizacion(int idMascota, int idVeterinario, int idCanil, String motivo) {
        this();
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.idCanil = idCanil;
        this.motivo = motivo;
    }

    public void darDeAlta() {
        this.estado = EstadoHospitalizacion.ALTA;
        this.fechaAlta = LocalDate.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }
    public int getIdCanil() { return idCanil; }
    public void setIdCanil(int idCanil) { this.idCanil = idCanil; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public EstadoHospitalizacion getEstado() { return estado; }
}