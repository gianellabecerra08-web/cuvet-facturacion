package cuvet.exception;
/** Encapsula errores JDBC. @author Becerra Huillcas, Gianella Emely (2411438) */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) { super(message); }
    public DatabaseException(String message, Throwable cause) { super(message, cause); }
}
