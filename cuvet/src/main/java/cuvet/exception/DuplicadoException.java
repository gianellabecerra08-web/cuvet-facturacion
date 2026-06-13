package cuvet.exception;
/** Lanzada al detectar servicio duplicado para la misma mascota y fecha. @author Becerra Huillcas, Gianella Emely (2411438) */
public class DuplicadoException extends RuntimeException {
    public DuplicadoException(String message) { super(message); }
    public DuplicadoException(String message, Throwable cause) { super(message, cause); }
}
