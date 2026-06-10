package cuvet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RangoFecha {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public RangoFecha() {}

    public RangoFecha(LocalDate inicio, LocalDate fin) {
        this.fechaInicio = inicio;
        this.fechaFin = fin;
    }

    public static RangoFecha desdePeriodo(Periodo periodo) {
        return new RangoFecha(periodo.getPrimerDia(), periodo.getUltimoDia());
    }

    public static RangoFecha hoy() {
        LocalDate hoy = LocalDate.now();
        return new RangoFecha(hoy, hoy);
    }

    public boolean contiene(LocalDate fecha) {
        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    public boolean contiene(LocalDateTime fechaHora) {
        return contiene(fechaHora.toLocalDate());
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate f) { this.fechaInicio = f; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate f) { this.fechaFin = f; }
}