package cuvet.database;

import cuvet.exception.DatabaseException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Verifica el estado de la conexión y disponibilidad de la base de datos.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class DatabaseHealthCheck extends AbstractJdbcRepository {
    public boolean estaDisponible() {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT 1")) {
            return rs.next() && rs.getInt(1) == 1;
        } catch (SQLException e) {
            return false;
        }
    }
    public String obtenerNombreBaseDatos() {
        try (Connection conn = getConnection()) {
            return conn.getCatalog();
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener nombre de base de datos", e);
        }
    }
    public boolean tablaExiste(String nombreTabla) {
        String sql = """
            SELECT COUNT(*)
            FROM information_schema.tables
            WHERE table_schema = ? AND table_name = ?
            """;
        try (var ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, DatabaseConstants.DB_NAME);
            ps.setString(2, nombreTabla);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al verificar existencia de tabla", e);
        }
        return false;
    }
}
