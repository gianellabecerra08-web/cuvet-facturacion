package cuvet.exception;

public class MascotaNoEncontradaException extends Exception {
    public MascotaNoEncontradaException(String mensaje) { super(mensaje); }
    public MascotaNoEncontradaException(String mensaje, Throwable causa) { super(mensaje, causa); }
}