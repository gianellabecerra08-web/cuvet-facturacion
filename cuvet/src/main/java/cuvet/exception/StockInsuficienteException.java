package cuvet.exception;
/** Lanzada cuando el stock no cubre la cantidad. @author Becerra Huillcas, Gianella Emely (2411438) */
public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) { super(message); }
    public StockInsuficienteException(String message, Throwable cause) { super(message, cause); }
}
