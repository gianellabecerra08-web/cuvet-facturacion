package cuvet.model;

import java.util.ArrayList;
import java.util.List;

public class ResumenMensual implements Calculable {
    private Periodo periodo;
    private List<TotalPorTipo> totalesPorTipo;
    private int totalAtenciones;
    private int totalFacturas;

    public ResumenMensual() { this.totalesPorTipo = new ArrayList<>(); }

    public ResumenMensual(Periodo periodo) {
        this();
        this.periodo = periodo;
    }

    public void agregarTotal(TotalPorTipo total) {
        if (total != null) totalesPorTipo.add(total);
    }

    @Override public double calcularSubtotal() {
        return totalesPorTipo.stream().mapToDouble(TotalPorTipo::getMontoTotal).sum();
    }
    @Override public double calcularIGV() { return calcularSubtotal() * 0.18; }
    @Override public double calcularTotal() { return calcularSubtotal() + calcularIGV(); }

    public Periodo getPeriodo() { return periodo; }
    public void setPeriodo(Periodo periodo) { this.periodo = periodo; }
    public List<TotalPorTipo> getTotalesPorTipo() { return totalesPorTipo; }
    public int getTotalAtenciones() { return totalAtenciones; }
    public void setTotalAtenciones(int t) { this.totalAtenciones = t; }
    public int getTotalFacturas() { return totalFacturas; }
    public void setTotalFacturas(int t) { this.totalFacturas = t; }
}