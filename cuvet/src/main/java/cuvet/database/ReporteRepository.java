package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.TipoServicio;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Consultas especializadas para reportes de facturación.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ReporteRepository extends AbstractJdbcRepository {
    public List<ReporteMensualDTO> obtenerIngresosPorTipo(int mes, int anio) {
        List<ReporteMensualDTO> lista = new ArrayList<>();
        String sql = """
            SELECT s.tipo, SUM(fi.subtotal) AS total
            FROM factura_items fi
            JOIN facturas f ON f.id = fi.id_factura
            JOIN servicios s ON s.id = fi.id_servicio
            WHERE MONTH(f.fecha) = ? AND YEAR(f.fecha) = ? AND f.estado = 'EMITIDA'
            GROUP BY s.tipo
            ORDER BY s.tipo
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ReporteMensualDTO(
                            TipoServicio.valueOf(rs.getString("tipo")),
                            rs.getBigDecimal("total")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener ingresos por tipo", e);
        }
        return lista;
    }
    public BigDecimal obtenerTotalMensual(int mes, int anio) {
        String sql = """
            SELECT COALESCE(SUM(total), 0) AS total
            FROM facturas
            WHERE MONTH(fecha) = ? AND YEAR(fecha) = ? AND estado = 'EMITIDA'
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("total");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener total mensual", e);
        }
        return BigDecimal.ZERO;
    }
    public int contarFacturasMensuales(int mes, int anio) {
        String sql = """
            SELECT COUNT(*) FROM facturas
            WHERE MONTH(fecha) = ? AND YEAR(fecha) = ? AND estado = 'EMITIDA'
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al contar facturas mensuales", e);
        }
        return 0;
    }
}
