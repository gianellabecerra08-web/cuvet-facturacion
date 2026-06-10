package cuvet.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Hospitalizacion extends Servicio {
    private int idMascota;
    private LocalDate fechaIngreso;
    private LocalDate fechaAlta;
    private String motivoIngreso;
    private String cuidadosDiarios;
    private double costoPorDia;

    public Hospitalizacion() { super(); }

    public Hospitalizacion(int idMascota, LocalDate fechaIngreso, String motivo, double costoPorDia) {
        super("Hospitalización", TipoServicio.hospitalizacion(), costoPorDia);
        this.idMascota = idMascota;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivo;
        this.costoPorDia = costoPorDia;
    }

    public long getDiasHospitalizado() {
        LocalDate fin = fechaAlta != null ? fechaAlta : LocalDate.now();
        return ChronoUnit.DAYS.between(fechaIngreso, fin);
    }

    public double calcularCostoTotal() { return getDiasHospitalizado() * costoPorDia; }

    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int id) { this.idMascota = id; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate f) { this.fechaIngreso = f; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate f) { this.fechaAlta = f; }
    public String getMotivoIngreso() { return motivoIngreso; }
    public void setMotivoIngreso(String m) { this.motivoIngreso = m; }
    public String getCuidadosDiarios() { return cuidadosDiarios; }
    public void setCuidadosDiarios(String c) { this.cuidadosDiarios = c; }
    public double getCostoPorDia() { return costoPorDia; }
    public void setCostoPorDia(double c) { this.costoPorDia = c; }
}