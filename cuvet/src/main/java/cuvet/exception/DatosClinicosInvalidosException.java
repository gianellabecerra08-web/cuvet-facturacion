package cuvet.exception;
/** Lanzada al registrar datos clínicos incompletos o inconsistentes. @author Becerra Huillcas, Gianella Emely (2411438) */
public class DatosClinicosInvalidosException extends RuntimeException {
    public DatosClinicosInvalidosException(String message) { super(message); }
    public DatosClinicosInvalidosException(String message, Throwable cause) { super(message, cause); }
}