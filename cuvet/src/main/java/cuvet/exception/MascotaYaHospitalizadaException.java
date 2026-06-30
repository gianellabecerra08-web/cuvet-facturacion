package cuvet.exception;
/** Lanzada si se intenta hospitalizar una mascota ya internada. @author Becerra Huillcas, Gianella Emely (2411438) */
public class MascotaYaHospitalizadaException extends RuntimeException {
    public MascotaYaHospitalizadaException(String message) { super(message); }
    public MascotaYaHospitalizadaException(String message, Throwable cause) { super(message, cause); }
}