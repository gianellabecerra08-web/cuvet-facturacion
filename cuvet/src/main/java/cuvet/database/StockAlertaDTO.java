package cuvet.database;

/**
 * DTO de alerta de stock bajo en medicamentos.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class StockAlertaDTO {
    private int idMedicamento;
    private String nombre;
    private int stockActual;
    private int stockMinimo;
    public StockAlertaDTO(int idMedicamento, String nombre, int stockActual, int stockMinimo) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }
    public int getIdMedicamento() { return idMedicamento; }
    public String getNombre() { return nombre; }
    public int getStockActual() { return stockActual; }
    public int getStockMinimo() { return stockMinimo; }
    public boolean esCritico() {
        return stockActual <= stockMinimo;
    }
}
