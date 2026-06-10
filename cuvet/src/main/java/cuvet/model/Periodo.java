package cuvet.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class Periodo {
    private int mes;
    private int anio;

    public Periodo() {}

    public Periodo(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
    }

    public static Periodo actual() {
        LocalDate hoy = LocalDate.now();
        return new Periodo(hoy.getMonthValue(), hoy.getYear());
    }

    public LocalDate getPrimerDia() { return LocalDate.of(anio, mes, 1); }
    public LocalDate getUltimoDia() { return YearMonth.of(anio, mes).atEndOfMonth(); }
    public String getDescripcion() { return Month.of(mes).name() + " " + anio; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
}