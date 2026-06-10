package cuvet.model;

import java.time.LocalDateTime;

public class AuditoriaLog {
    private int id;
    private String usuario;
    private String accion;
    private String tabla;
    private int idRegistro;
    private String valorAnterior;
    private String valorNuevo;
    private LocalDateTime fechaHora;
    private String ipOrigen;

    public AuditoriaLog() { this.fechaHora = LocalDateTime.now(); }

    public AuditoriaLog(String usuario, String accion, String tabla, int idRegistro) {
        this();
        this.usuario = usuario;
        this.accion = accion;
        this.tabla = tabla;
        this.idRegistro = idRegistro;
    }

    public String getResumen() {
        return "[" + fechaHora + "] " + usuario + " realizó " + accion + " en " + tabla + " (ID=" + idRegistro + ")";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getTabla() { return tabla; }
    public void setTabla(String tabla) { this.tabla = tabla; }
    public int getIdRegistro() { return idRegistro; }
    public void setIdRegistro(int id) { this.idRegistro = id; }
    public String getValorAnterior() { return valorAnterior; }
    public void setValorAnterior(String v) { this.valorAnterior = v; }
    public String getValorNuevo() { return valorNuevo; }
    public void setValorNuevo(String v) { this.valorNuevo = v; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime f) { this.fechaHora = f; }
    public String getIpOrigen() { return ipOrigen; }
    public void setIpOrigen(String ip) { this.ipOrigen = ip; }
}