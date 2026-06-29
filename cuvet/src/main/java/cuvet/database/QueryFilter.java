package cuvet.database;

/**
 * Filtro reutilizable para consultas dinámicas.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class QueryFilter {
    private String campo;
    private String operador;
    private Object valor;
    private boolean activo;
    public QueryFilter() {
        this.activo = true;
    }
    public QueryFilter(String campo, String operador, Object valor) {
        this.campo = campo;
        this.operador = operador;
        this.valor = valor;
        this.activo = true;
    }
    public String getCampo() { return campo; }
    public void setCampo(String campo) { this.campo = campo; }
    public String getOperador() { return operador; }
    public void setOperador(String operador) { this.operador = operador; }
    public Object getValor() { return valor; }
    public void setValor(Object valor) { this.valor = valor; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public String toSqlFragment() {
        if (!activo || campo == null || operador == null) {
            return "";
        }
        return campo + " " + operador + " ?";
    }
}
