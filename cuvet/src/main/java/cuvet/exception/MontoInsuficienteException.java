package cuvet.exception;
/** Lanzada si el pago registrado es menor al total de la factura. @author Becerra Huillcas, Gianella Emely (2411438) */
public class MontoInsuficienteException extends RuntimeException {
    public MontoInsuficienteException(String message) { super(message); }
    public MontoInsuficienteException(String message, Throwable cause) { super(message, cause); }
}