package cuvet.exception;
/** Lanzada cuando no hay caniles disponibles para hospitalizar. @author Becerra Huillcas, Gianella Emely (2411438) */
public class SinCanilDisponibleException extends RuntimeException {
    public SinCanilDisponibleException(String message) { super(message); }
    public SinCanilDisponibleException(String message, Throwable cause) { super(message, cause); }
}