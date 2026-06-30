package cuvet.model;

import java.time.LocalDateTime;

/**
 * Nota periódica de monitoreo durante una hospitalización.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class MonitoreoClinico {
    private int id;
    private int idHospitalizacion;
    private LocalDateTime fechaHora;
    private double temperatura;
    private int frecuenciaCardiaca;
    private String estadoPaciente;
    private String observaciones;

    public MonitoreoClinico() { this.fechaHora = LocalDateTime.now(); }

    public MonitoreoClinico(int idHospitalizacion, double temperatura, int frecuenciaCardiaca, String estadoPaciente) {
        this();
        this.idHospitalizacion = idHospitalizacion;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.estadoPaciente = estadoPaciente;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdHospitalizacion() { return idHospitalizacion; }
    public void setIdHospitalizacion(int idHospitalizacion) { this.idHospitalizacion = idHospitalizacion; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public int getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }
    public String getEstadoPaciente() { return estadoPaciente; }
    public void setEstadoPaciente(String estadoPaciente) { this.estadoPaciente = estadoPaciente; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}