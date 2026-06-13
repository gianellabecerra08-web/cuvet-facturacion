package cuvet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Atencion médica. Núcleo del flujo principal del sistema.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Atencion {
    private int id;
    private LocalDate fecha;
    private int idCliente;
    private int idMascota;
    private int idVeterinario;
    private String observaciones;
    private List<Servicio> servicios;

    public Atencion() {
        this.fecha = LocalDate.now();
        this.servicios = new ArrayList<>();
    }

    public Atencion(int idCliente, int idMascota, int idVeterinario, String observaciones) {
        this.fecha = LocalDate.now();
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.observaciones = observaciones;
        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(Servicio s) { servicios.add(s); }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
}
