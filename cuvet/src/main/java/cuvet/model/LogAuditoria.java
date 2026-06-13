package cuvet.model;

import java.time.LocalDateTime;

/**
 * Registro de auditoría inmutable por operación del sistema.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class LogAuditoria {
    private int id;
    private int idUsuario;
    private String accion;   // INSERT, UPDATE, DELETE, LOGIN, LOGOUT
    private String tabla;
    private int idRegistro;
    private LocalDateTime timestamp;
    private String detalle;

    public LogAuditoria() { this.timestamp = LocalDateTime.now(); }

    public LogAuditoria(int idUsuario, String accion, String tabla, int idRegistro, String detalle) {
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.detalle = detalle;
        this.timestamp = LocalDateTime.now();
    }

    // Getters (sin setters — registro inmutable salvo id)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public String getAccion() { return accion; }
    public String getTabla() { return tabla; }
    public int getIdRegistro() { return idRegistro; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDetalle() { return detalle; }

    @Override
    public String toString() {
        return timestamp + " | " + accion + " | " + tabla + "#" + idRegistro + " | " + detalle;
    }
}
