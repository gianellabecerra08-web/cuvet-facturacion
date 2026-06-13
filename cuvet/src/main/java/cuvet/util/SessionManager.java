package cuvet.util;

import cuvet.model.Usuario;

/**
 * Singleton que gestiona el usuario autenticado en memoria durante la sesión.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class SessionManager {
    private static volatile SessionManager instancia;
    private Usuario usuarioActual;

    private SessionManager() {}

    public static SessionManager getInstancia() {
        if (instancia == null) {
            synchronized (SessionManager.class) {
                if (instancia == null) instancia = new SessionManager();
            }
        }
        return instancia;
    }

    public void iniciarSesion(Usuario usuario) { this.usuarioActual = usuario; }
    public void cerrarSesion() { this.usuarioActual = null; }
    public Usuario getUsuarioActual() { return usuarioActual; }
    public boolean haySession() { return usuarioActual != null; }
}
