package cuvet.exception;
/** Lanzada al autenticar usuario inactivo. @author Becerra Huillcas, Gianella Emely (2411438) */
public class UsuarioInactivoException extends RuntimeException {
    public UsuarioInactivoException(String message) { super(message); }
    public UsuarioInactivoException(String message, Throwable cause) { super(message, cause); }
}
