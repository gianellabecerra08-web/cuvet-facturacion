package cuvet.database;

import cuvet.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad genérica para ejecutar consultas parametrizadas.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class SqlExecutor extends AbstractJdbcRepository {
    public int ejecutarUpdate(String sql, SqlParameterSetter setter) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            if (setter != null) {
                setter.setValues(ps);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al ejecutar update", e);
        }
    }
    public <T> List<T> ejecutarQuery(String sql, SqlParameterSetter setter, RowMapper<T> mapper) {
        List<T> lista = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            if (setter != null) {
                setter.setValues(ps);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al ejecutar query", e);
        }
        return lista;
    }
    public long contar(String sql, SqlParameterSetter setter) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            if (setter != null) {
                setter.setValues(ps);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al contar registros", e);
        }
        return 0L;
    }
    @FunctionalInterface
    public interface SqlParameterSetter {
        void setValues(PreparedStatement ps) throws SQLException;
    }
}
