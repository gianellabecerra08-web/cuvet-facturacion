package cuvet.exception;
/** Lanzada al intentar consultar resultados de un examen aún pendiente. @author Becerra Huillcas, Gianella Emely (2411438) */
public class ExamenNoCompletadoException extends RuntimeException {
    public ExamenNoCompletadoException(String message) { super(message); }
    public ExamenNoCompletadoException(String message, Throwable cause) { super(message, cause); }
}