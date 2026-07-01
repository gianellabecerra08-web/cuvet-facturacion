package cuvet.exception;
/** Lanzada al recibir una orden ya procesada o sin ítems. @author Becerra Huillcas, Gianella Emely (2411438) */
public class OrdenCompraInvalidaException extends RuntimeException {
    public OrdenCompraInvalidaException(String message) { super(message); }
    public OrdenCompraInvalidaException(String message, Throwable cause) { super(message, cause); }
}