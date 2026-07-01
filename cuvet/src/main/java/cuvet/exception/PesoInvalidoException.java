package cuvet.exception;
/** Lanzada al registrar un peso fuera de rango válido para la especie. @author Becerra Huillcas, Gianella Emely (2411438) */
public class PesoInvalidoException extends RuntimeException {
    public PesoInvalidoException(String message) { super(message); }
    public PesoInvalidoException(String message, Throwable cause) { super(message, cause); }
}