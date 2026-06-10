package cuvet.model;

public class TotalPorTipo {
    private TipoServicio tipoServicio;
    private int cantidadServicios;
    private double montoTotal;
    private double porcentajeDelTotal;

    public TotalPorTipo() {}

    public TotalPorTipo(TipoServicio tipoServicio, int cantidad, double monto) {
        this.tipoServicio = tipoServicio;
        this.cantidadServicios = cantidad;
        this.montoTotal = monto;
    }

    public void calcularPorcentaje(double totalGeneral) {
        this.porcentajeDelTotal = totalGeneral > 0 ? (montoTotal / totalGeneral) * 100 : 0;
    }

    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio t) { this.tipoServicio = t; }
    public int getCantidadServicios() { return cantidadServicios; }
    public void setCantidadServicios(int c) { this.cantidadServicios = c; }
    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double m) { this.montoTotal = m; }
    public double getPorcentajeDelTotal() { return porcentajeDelTotal; }
    public void setPorcentajeDelTotal(double p) { this.porcentajeDelTotal = p; }
}