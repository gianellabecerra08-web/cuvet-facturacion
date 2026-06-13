package cuvet.exception;
/** Lanzada cuando no hay datos en el período. @author Becerra Huillcas, Gianella Emely (2411438) */
public class ReporteVacioException extends RuntimeException {
    public ReporteVacioException(String message) { super(message); }
    public ReporteVacioException(String message, Throwable cause) { super(message, cause); }
}
