package cuvet.exception;
/** Lanzada cuando no se encuentra un servicio. @author Becerra Huillcas, Gianella Emely (2411438) */
public class ServicioNotFoundException extends RuntimeException {
    public ServicioNotFoundException(String message) { super(message); }
    public ServicioNotFoundException(String message, Throwable cause) { super(message, cause); }
}
