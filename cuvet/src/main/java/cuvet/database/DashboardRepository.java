package cuvet.database;

import cuvet.exception.DatabaseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Métricas generales del sistema para panel administrativo.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class DashboardRepository extends AbstractJdbcRepository {
    public long contarClientes() {
        return contar("SELECT COUNT(*) FROM clientes");
    }
    public long contarMascotas() {
        return contar("SELECT COUNT(*) FROM mascotas");
    }
    public long contarAtenciones() {
        return contar("SELECT COUNT(*) FROM atenciones");
    }
    public long contarFacturasEmitidas() {
        return contar("SELECT COUNT(*) FROM facturas WHERE estado='EMITIDA'");
    }
    public long contarCitasPendientes() {
        return contar("SELECT COUNT(*) FROM citas WHERE estado='PENDIENTE'");
    }
    public long contarMedicamentosStockBajo() {
        return contar("SELECT COUNT(*) FROM medicamentos WHERE stock_actual <= stock_minimo AND activo=true");
    }
    private long contar(String sql) {
        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al contar métricas del dashboard", e);
        }
        return 0L;
    }
}

