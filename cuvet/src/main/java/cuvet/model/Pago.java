package cuvet.model;

import java.time.LocalDateTime;

public class Pago {
    private int id;
    private int idFactura;
    private double monto;
    private MetodoPago metodoPago;
    private LocalDateTime fechaPago;
    private EstadoPago estado;
    private String referencia;
    private String observaciones;

    public Pago() {
        this.fechaPago = LocalDateTime.now();
        this.estado = EstadoPago.PENDIENTE;
    }

    public Pago(int idFactura, double monto, MetodoPago metodoPago) {
        this();
        this.idFactura = idFactura;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public void completar() { this.estado = EstadoPago.COMPLETADO; }
    public void rechazar() { this.estado = EstadoPago.RECHAZADO; }
    public void reembolsar() { this.estado = EstadoPago.REEMBOLSADO; }
    public boolean estaCompletado() { return estado == EstadoPago.COMPLETADO; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int id) { this.idFactura = id; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago m) { this.metodoPago = m; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime f) { this.fechaPago = f; }
    public EstadoPago getEstado() { return estado; }
    public void setEstado(EstadoPago estado) { this.estado = estado; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String r) { this.referencia = r; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String o) { this.observaciones = o; }
}