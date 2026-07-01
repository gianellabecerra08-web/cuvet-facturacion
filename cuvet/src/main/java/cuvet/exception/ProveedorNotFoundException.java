package cuvet.exception;
/** Lanzada al buscar un proveedor inexistente. @author Becerra Huillcas, Gianella Emely (2411438) */
public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(String message) { super(message); }
    public ProveedorNotFoundException(String message, Throwable cause) { super(message, cause); }
}