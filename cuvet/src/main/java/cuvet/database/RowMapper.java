package cuvet.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contrato funcional para mapear filas SQL a objetos de dominio.
 * @param <T> Tipo de entidad
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}