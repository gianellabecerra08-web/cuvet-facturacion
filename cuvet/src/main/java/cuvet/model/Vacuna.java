package cuvet.model;

import java.time.LocalDate;

public class Vacuna extends Servicio {
    private String fabricante;
    private String lote;
    private LocalDate fechaVencimiento;
    private int mesesProteccion;
    private String enfermadadesCubre;

    public Vacuna() { super(); }

    public Vacuna(String descripcion, double precioBase, String fabricante, String lote, int mesesProteccion) {
        super(descripcion, TipoServicio.vacuna(), precioBase);
        this.fabricante = fabricante;
        this.lote = lote;
        this.mesesProteccion = mesesProteccion;
    }

    public LocalDate calcularProximaAplicacion(LocalDate fechaAplicacion) {
        return fechaAplicacion.plusMonths(mesesProteccion);
    }

    public boolean estaVencida() {
        return fechaVencimiento != null && LocalDate.now().isAfter(fechaVencimiento);
    }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String f) { this.fabricante = f; }
    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate f) { this.fechaVencimiento = f; }
    public int getMesesProteccion() { return mesesProteccion; }
    public void setMesesProteccion(int m) { this.mesesProteccion = m; }
    public String getEnfermadadesCubre() { return enfermadadesCubre; }
    public void setEnfermadadesCubre(String e) { this.enfermadadesCubre = e; }
}