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
    /** Persiste una nueva entidad. Retorna la entidad con el ID generado. */
    T guardar(T entidad);

    /** Busca por ID. Retorna Optional vacío si no existe. */
    Optional<T> buscarPorId(ID id);

    /** Retorna todos los registros activos. */
    List<T> listarTodos();

    /** Actualiza los datos de la entidad. */
    void actualizar(T entidad);

    /** Elimina (o desactiva) la entidad por ID. */
    void eliminar(ID id);
}
