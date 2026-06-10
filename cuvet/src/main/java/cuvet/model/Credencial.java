package cuvet.model;

import java.time.LocalDateTime;

public class Credencial {
    private String username;
    private String password;
    private String token;
    private LocalDateTime expiracion;
    private boolean activa;

    public Credencial() { this.activa = true; }

    public Credencial(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public boolean estaExpirada() {
        return expiracion != null && LocalDateTime.now().isAfter(expiracion);
    }

    public boolean esValida() { return activa && !estaExpirada(); }

    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public LocalDateTime getExpiracion() { return expiracion; }
    public void setExpiracion(LocalDateTime e) { this.expiracion = e; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}