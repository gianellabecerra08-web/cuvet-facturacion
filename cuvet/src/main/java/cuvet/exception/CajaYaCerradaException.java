package cuvet.exception;
/** Lanzada al intentar operar sobre un turno de caja ya cerrado. @author Becerra Huillcas, Gianella Emely (2411438) */
public class CajaYaCerradaException extends RuntimeException {
    public CajaYaCerradaException(String message) { super(message); }
    public CajaYaCerradaException(String message, Throwable cause) { super(message, cause); }
}