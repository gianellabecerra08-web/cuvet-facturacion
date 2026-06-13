package cuvet.exception;
/** Lanzada cuando no se encuentra un cliente. @author Becerra Huillcas, Gianella Emely (2411438) */
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) { super(message); }
    public ClienteNotFoundException(String message, Throwable cause) { super(message, cause); }
}
