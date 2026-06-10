package cuvet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atencion implements Auditable {
    private int id;
    private int idCliente;
    private int idMascota;
    private int idVeterinario;
    private LocalDateTime fechaAtencion;
    private EstadoAtencion estado;
    private String observaciones;
    private List<Servicio> servicios;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;

    public Atencion() {
        this.servicios = new ArrayList<>();
        this.estado = EstadoAtencion.REGISTRADA;
        this.fechaAtencion = LocalDateTime.now();
    }

    public void agregarServicio(Servicio servicio) {
        if (servicio != null && !servicios.contains(servicio)) servicios.add(servicio);
    }

    public void removerServicio(Servicio servicio) { servicios.remove(servicio); }

    public double calcularMontoTotal() {
        return servicios.stream().mapToDouble(Servicio::getPrecioBase).sum();
    }

    @Override public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    @Override public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    @Override public String getUsuarioCreacion() { return usuarioCreacion; }
    @Override public void setFechaCreacion(LocalDateTime f) { this.fechaCreacion = f; }
    @Override public void setFechaModificacion(LocalDateTime f) { this.fechaModificacion = f; }
    @Override public void setUsuarioCreacion(String u) { this.usuarioCreacion = u; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int id) { this.idCliente = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int id) { this.idMascota = id; }
    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int id) { this.idVeterinario = id; }
    public LocalDateTime getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDateTime f) { this.fechaAtencion = f; }
    public EstadoAtencion getEstado() { return estado; }
    public void setEstado(EstadoAtencion estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String o) { this.observaciones = o; }
    public List<Servicio> getServicios() { return servicios; }
}
