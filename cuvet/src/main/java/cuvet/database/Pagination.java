package cuvet.database;

import java.util.Collections;
import java.util.List;

/**
 * Resultado paginado de consultas JDBC.
 * @param <T> Tipo de elemento
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class Pagination<T> {
    private final List<T> contenido;
    private final int pagina;
    private final int tamanoPagina;
    private final long totalElementos;
    public Pagination(List<T> contenido, int pagina, int tamanoPagina, long totalElementos) {
        this.contenido = contenido != null ? contenido : Collections.emptyList();
        this.pagina = pagina;
        this.tamanoPagina = tamanoPagina;
        this.totalElementos = totalElementos;
    }
    public List<T> getContenido() { return contenido; }
    public int getPagina() { return pagina; }
    public int getTamanoPagina() { return tamanoPagina; }
    public long getTotalElementos() { return totalElementos; }
    public int getTotalPaginas() {
        if (tamanoPagina <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalElementos / tamanoPagina);
    }
    public int getOffset() {
        return Math.max(0, (pagina - 1) * tamanoPagina);
    }
}
