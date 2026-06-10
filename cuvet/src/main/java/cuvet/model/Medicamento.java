package cuvet.model;

import java.time.LocalDate;

public class Medicamento extends Servicio {
    private String principioActivo;
    private String laboratorio;
    private String concentracion;
    private String formaFarmaceutica;
    private int stockDisponible;
    private LocalDate fechaVencimiento;

    public Medicamento() { super(); }

    public Medicamento(String descripcion, double precioBase, String principioActivo, int stock) {
        super(descripcion, TipoServicio.medicamento(), precioBase);
        this.principioActivo = principioActivo;
        this.stockDisponible = stock;
    }

    public boolean estaVencido() {
        return fechaVencimiento != null && LocalDate.now().isAfter(fechaVencimiento);
    }

    public boolean hayStock(int cantidad) { return stockDisponible >= cantidad; }

    public void reducirStock(int cantidad) {
        if (hayStock(cantidad)) stockDisponible -= cantidad;
    }

    public String getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(String p) { this.principioActivo = p; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String l) { this.laboratorio = l; }
    public String getConcentracion() { return concentracion; }
    public void setConcentracion(String c) { this.concentracion = c; }
    public String getFormaFarmaceutica() { return formaFarmaceutica; }
    public void setFormaFarmaceutica(String f) { this.formaFarmaceutica = f; }
    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int s) { this.stockDisponible = s; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate f) { this.fechaVencimiento = f; }
}