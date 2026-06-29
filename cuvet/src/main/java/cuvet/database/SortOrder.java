package cuvet.database;

/**
 * Ordenamiento para consultas SQL.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class SortOrder {
    private final String campo;
    private final Direccion direccion;
    public enum Direccion {
        ASC, DESC
    }
    public SortOrder(String campo, Direccion direccion) {
        this.campo = campo;
        this.direccion = direccion;
    }
    public String getCampo() { return campo; }
    public Direccion getDireccion() { return direccion; }
    public String toSqlFragment() {
        return campo + " " + direccion.name();
    }
    public static SortOrder asc(String campo) {
        return new SortOrder(campo, Direccion.ASC);
    }
    public static SortOrder desc(String campo) {
        return new SortOrder(campo, Direccion.DESC);
    }
}
