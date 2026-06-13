package cuvet.model;

import java.time.LocalDate;

/**
 * Registro de vacunación por mascota.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Vacuna {
    private int id;
    private int idMascota;
    private String nombreVacuna;
    private String lote;
    private LocalDate fechaAplicacion;
    private LocalDate fechaProxima;
    private int idVeterinario;

    public Vacuna() {}

    public Vacuna(int idMascota, String nombreVacuna, String lote,
                  LocalDate fechaAplicacion, LocalDate fechaProxima, int idVeterinario) {
        this.idMascota = idMascota;
        this.nombreVacuna = nombreVacuna;
        this.lote = lote;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaProxima = fechaProxima;
        this.idVeterinario = idVeterinario;
    }

    public boolean estaVencida() {
        return fechaProxima != null && LocalDate.now().isAfter(fechaProxima);
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public String getNombreVacuna() { return nombreVacuna; }
    public void setNombreVacuna(String nombreVacuna) { this.nombreVacuna = nombreVacuna; }
    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }
    public LocalDate getFechaProxima() { return fechaProxima; }
    public void setFechaProxima(LocalDate fechaProxima) { this.fechaProxima = fechaProxima; }
    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }
}
