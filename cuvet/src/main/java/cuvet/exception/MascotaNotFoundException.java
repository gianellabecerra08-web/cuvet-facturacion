package cuvet.exception;
/** Lanzada cuando no se encuentra una mascota. @author Becerra Huillcas, Gianella Emely (2411438) */
public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(String message) { super(message); }
    public MascotaNotFoundException(String message, Throwable cause) { super(message, cause); }
}
