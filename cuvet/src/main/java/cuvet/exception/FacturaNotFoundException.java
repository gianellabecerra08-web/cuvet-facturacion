package cuvet.exception;
/** Lanzada cuando no se encuentra una factura. @author Becerra Huillcas, Gianella Emely (2411438) */
public class FacturaNotFoundException extends RuntimeException {
    public FacturaNotFoundException(String message) { super(message); }
    public FacturaNotFoundException(String message, Throwable cause) { super(message, cause); }
}
