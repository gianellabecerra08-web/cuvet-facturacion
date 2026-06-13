package cuvet.model;

import java.time.LocalDate;

/**
 * Registro clínico por atención.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class HistorialClinico {
    private int id;
    private int idAtencion;
    private int idMascota;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private LocalDate fecha;

    public HistorialClinico() { this.fecha = LocalDate.now(); }

    public HistorialClinico(int idAtencion, int idMascota, String diagnostico,
                             String tratamiento, String observaciones) {
        this.idAtencion = idAtencion;
        this.idMascota = idMascota;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.fecha = LocalDate.now();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
