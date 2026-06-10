package cuvet.exception;

public class FacturaYaEmitidaException extends Exception {
    public FacturaYaEmitidaException(String mensaje) { super(mensaje); }
    public FacturaYaEmitidaException(String mensaje, Throwable causa) { super(mensaje, causa); }
}