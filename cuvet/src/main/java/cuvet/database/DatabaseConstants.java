package cuvet.database;

/**
 * Constantes de tablas y columnas usadas en la capa de persistencia.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class DatabaseConstants {
    public static final String DB_NAME = "cuvet_db";
    public static final double TASA_IGV = 0.18;
    public static final String ESTADO_FACTURA_EMITIDA = "EMITIDA";
    public static final String ESTADO_FACTURA_ANULADA = "ANULADA";
    public static final String PREFIJO_FACTURA = "F001-";
    private DatabaseConstants() {}
}
