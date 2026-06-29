package cuvet.controller;

import cuvet.database.MedicamentoRepository;
import cuvet.exception.StockInsuficienteException;
import cuvet.model.Medicamento;
import java.util.List;

/**
 * Controla el inventario de medicamentos: descuento y reposición de stock.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class MedicamentoController {
    private final MedicamentoRepository medicamentoRepo;

    public MedicamentoController() { this.medicamentoRepo = new MedicamentoRepository(); }

    public Medicamento registrar(Medicamento m) { medicamentoRepo.guardar(m); return m; }

    public List<Medicamento> listarTodos() { return medicamentoRepo.listarTodos(); }

    public List<Medicamento> listarStockBajo() { return medicamentoRepo.listarStockBajo(); }

    public void descontarStock(int idMedicamento, int cantidad) {
        Medicamento m = medicamentoRepo.buscarPorId(idMedicamento)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado: " + idMedicamento));
        if (cantidad > m.getStockActual())
            throw new StockInsuficienteException("Stock insuficiente para el medicamento: " + m.getNombre());
        m.descontarStock(cantidad);
        medicamentoRepo.actualizarStock(idMedicamento, m.getStockActual());
    }

    public void reponerStock(int idMedicamento, int cantidad) {
        Medicamento m = medicamentoRepo.buscarPorId(idMedicamento)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado: " + idMedicamento));
        m.reponerStock(cantidad);
        medicamentoRepo.actualizarStock(idMedicamento, m.getStockActual());
    }
}
