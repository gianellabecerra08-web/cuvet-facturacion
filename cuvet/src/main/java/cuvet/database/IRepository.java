package cuvet.database;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica Repository con contrato CRUD.
 * Implementa el patrón Template Method para acceso uniforme a datos.
 * @param <T>  Tipo de entidad
 * @param <ID> Tipo del identificador
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public interface IRepository<T, ID> {
    T guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
    default boolean existePorId(ID id) {
        return buscarPorId(id).isPresent();
    }
    default long contarTodos() {
        return listarTodos().size();
    }
}