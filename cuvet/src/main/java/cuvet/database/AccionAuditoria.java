package cuvet.database;

/**
 * Acciones permitidas en el log de auditoría.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public enum AccionAuditoria {
    INSERT,
    UPDATE,
    DELETE,
    LOGIN,
    LOGOUT;
    public String getValor() {
        return name();
    }
}
