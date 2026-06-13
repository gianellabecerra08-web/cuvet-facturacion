package cuvet.controller;

import cuvet.database.UsuarioRepository;
import cuvet.exception.AuthException;
import cuvet.exception.UsuarioInactivoException;
import cuvet.model.Rol;
import cuvet.model.Usuario;
import cuvet.util.AuditoriaLogger;
import cuvet.util.PasswordUtil;
import cuvet.util.SessionManager;

/**
 * Controlador de autenticación. Gestiona intentos fallidos y acceso por rol.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class AuthController {

    private static final int MAX_INTENTOS = 3;

    private final UsuarioRepository usuarioRepo;
    private final SessionManager session;
    private final AuditoriaLogger auditoria;

    private int intentosFallidos = 0;
    private boolean bloqueado = false;

    public AuthController() {
        this.usuarioRepo = new UsuarioRepository();
        this.session = SessionManager.getInstancia();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    /**
     * Autentica al usuario. Lanza AuthException si las credenciales son inválidas.
     * @param username nombre de usuario
     * @param password contraseña en texto plano (se hashea internamente)
     * @return Usuario autenticado
     */
    public Usuario login(String username, String password) {
        if (bloqueado) {
            throw new AuthException("Cuenta bloqueada por " + MAX_INTENTOS + " intentos fallidos. Contacte al administrador.");
        }

        String hash = PasswordUtil.hashear(password);

        Usuario usuario = usuarioRepo.autenticar(username, hash)
                .orElseThrow(() -> {
                    intentosFallidos++;
                    if (intentosFallidos >= MAX_INTENTOS) bloqueado = true;
                    return new AuthException("Credenciales incorrectas. Intento " + intentosFallidos + "/" + MAX_INTENTOS);
                });

        if (!usuario.isActivo()) throw new UsuarioInactivoException(username);

        intentosFallidos = 0;
        session.iniciarSesion(usuario);
        auditoria.registrar(usuario.getId(), "LOGIN", "usuarios", usuario.getId(), "Sesión iniciada: " + username);
        return usuario;
    }

    public void logout() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario != null) {
            auditoria.registrar(usuario.getId(), "LOGOUT", "usuarios", usuario.getId(), "Sesión cerrada: " + usuario.getUsername());
            session.cerrarSesion();
        }
    }

    /**
     * Verifica que el usuario actual tenga el rol requerido.
     * @throws AuthException si no tiene permisos.
     */
    public void verificarPermiso(Rol rolRequerido) {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) throw new AuthException("No hay sesión activa.");
        if (!usuario.tienePermiso(rolRequerido)) {
            throw new AuthException("Permiso denegado. Se requiere rol: " + rolRequerido);
        }
    }

    public boolean estaBloqueado() { return bloqueado; }

    public int getIntentosFallidos() { return intentosFallidos; }
}
