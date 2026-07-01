package cuvet.model;

import java.time.LocalDate;

/**
 * Recordatorio de próxima vacuna o control para una mascota.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class RecordatorioVacuna {
    private int id;
    private int idMascota;
    private String tipoVacuna;
    private LocalDate fechaProgramada;
    private boolean notificado;

    public RecordatorioVacuna() { this.notificado = false; }

    public RecordatorioVacuna(int idMascota, String tipoVacuna, LocalDate fechaProgramada) {
        this();
        this.idMascota = idMascota;
        this.tipoVacuna = tipoVacuna;
        this.fechaProgramada = fechaProgramada;
    }

    public boolean estaProximo() {
        return !notificado && LocalDate.now().isAfter(fechaProgramada.minusDays(7));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public String getTipoVacuna() { return tipoVacuna; }
    public void setTipoVacuna(String tipoVacuna) { this.tipoVacuna = tipoVacuna; }
    public LocalDate getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDate fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public boolean isNotificado() { return notificado; }
    public void setNotificado(boolean notificado) { this.notificado = notificado; }
}