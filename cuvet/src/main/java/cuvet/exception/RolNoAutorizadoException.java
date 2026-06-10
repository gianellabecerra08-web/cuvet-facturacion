package cuvet.exception;

public class RolNoAutorizadoException extends Exception {
    public RolNoAutorizadoException(String mensaje) { super(mensaje); }
    public RolNoAutorizadoException(String mensaje, Throwable causa) { super(mensaje, causa); }
}