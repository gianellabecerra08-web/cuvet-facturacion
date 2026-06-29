package cuvet.database;

import cuvet.exception.DatabaseException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Estadísticas operativas del sistema CUVET.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class EstadisticasRepository extends AbstractJdbcRepository {
    public List<IngresoDiarioDTO> ingresosUltimosDias(int dias) {
        List<IngresoDiarioDTO> lista = new ArrayList<>();
        String sql = """
            SELECT fecha, SUM(total) AS total
            FROM facturas
            WHERE estado = 'EMITIDA' AND fecha >= DATE_SUB(CURDATE(), INTERVAL ? DAY)
            GROUP BY fecha
            ORDER BY fecha
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, dias);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new IngresoDiarioDTO(
                            rs.getDate("fecha").toLocalDate(),
                            rs.getBigDecimal("total")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener ingresos diarios", e);
        }
        return lista;
    }
    public int contarAtencionesPorFecha(LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM atenciones WHERE fecha = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al contar atenciones por fecha", e);
        }
        return 0;
    }
    public BigDecimal promedioFacturaMensual(int mes, int anio) {
        String sql = """
            SELECT COALESCE(AVG(total), 0) AS promedio
            FROM facturas
            WHERE MONTH(fecha) = ? AND YEAR(fecha) = ? AND estado = 'EMITIDA'
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("promedio");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al calcular promedio mensual", e);
        }
        return BigDecimal.ZERO;
    }
}
