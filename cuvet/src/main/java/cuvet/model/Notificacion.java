package cuvet.model;

import java.time.LocalDateTime;

public class Notificacion {
    private int id;
    private String titulo;
    private String mensaje;
    private String tipo;
    private int idUsuarioDestino;
    private boolean leida;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaLectura;

    public Notificacion() {
        this.leida = false;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Notificacion(String titulo, String mensaje, String tipo, int idUsuarioDestino) {
        this();
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.idUsuarioDestino = idUsuarioDestino;
    }

    public void marcarLeida() {
        this.leida = true;
        this.fechaLectura = LocalDateTime.now();
    }

    public static Notificacion alerta(String mensaje, int idUsuario) {
        return new Notificacion("Alerta", mensaje, "ALERTA", idUsuario);
    }

    public static Notificacion info(String mensaje, int idUsuario) {
        return new Notificacion("Información", mensaje, "INFO", idUsuario);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getIdUsuarioDestino() { return idUsuarioDestino; }
    public void setIdUsuarioDestino(int id) { this.idUsuarioDestino = id; }
    public boolean isLeida() { return leida; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaLectura() { return fechaLectura; }
}