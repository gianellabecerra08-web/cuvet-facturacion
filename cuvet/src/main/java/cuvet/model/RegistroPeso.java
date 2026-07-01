package cuvet.model;

import java.time.LocalDate;

/**
 * Registro de peso histórico de una mascota para seguimiento nutricional.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class RegistroPeso {
    private int id;
    private int idMascota;
    private double pesoKg;
    private LocalDate fecha;

    public RegistroPeso() { this.fecha = LocalDate.now(); }

    public RegistroPeso(int idMascota, double pesoKg) {
        this();
        this.idMascota = idMascota;
        this.pesoKg = pesoKg;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public LocalDate getFecha() { return fecha; }
}