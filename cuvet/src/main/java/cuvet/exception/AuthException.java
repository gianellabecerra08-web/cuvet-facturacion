package cuvet.exception;
/** Lanzada por credenciales inválidas o permisos insuficientes. @author Becerra Huillcas, Gianella Emely (2411438) */
public class AuthException extends RuntimeException {
    public AuthException(String message) { super(message); }
    public AuthException(String message, Throwable cause) { super(message, cause); }
}
