package cuvet.exception;

public class ClienteNoEncontradaException extends Exception {
    public ClienteNoEncontradaException(String mensaje) { super(mensaje); }
    public ClienteNoEncontradaException(String mensaje, Throwable causa) { super(mensaje, causa); }
}