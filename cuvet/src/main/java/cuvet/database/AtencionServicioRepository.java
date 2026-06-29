package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de la tabla intermedia atencion_servicios.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class AtencionServicioRepository extends AbstractJdbcRepository {
    public void vincular(int idAtencion, int idServicio) {
        String sql = "INSERT INTO atencion_servicios (id_atencion, id_servicio) VALUES (?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            ps.setInt(2, idServicio);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al vincular servicio a atención", e);
        }
    }
    public void desvincular(int idAtencion, int idServicio) {
        String sql = "DELETE FROM atencion_servicios WHERE id_atencion=? AND id_servicio=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            ps.setInt(2, idServicio);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al desvincular servicio de atención", e);
        }
    }
    public List<Servicio> listarServiciosPorAtencion(int idAtencion) {
        List<Servicio> lista = new ArrayList<>();
        String sql = """
            SELECT s.id, s.tipo, s.descripcion, s.precio, s.activo
            FROM atencion_servicios ats
            JOIN servicios s ON s.id = ats.id_servicio
            WHERE ats.id_atencion = ?
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Servicio s = new Servicio(
                            rs.getInt("id"),
                            TipoServicio.valueOf(rs.getString("tipo")),
                            rs.getString("descripcion"),
                            rs.getBigDecimal("precio")
                    );
                    s.setActivo(rs.getBoolean("activo"));
                    lista.add(s);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar servicios de atención", e);
        }
        return lista;
    }
    public void eliminarPorAtencion(int idAtencion) {
        String sql = "DELETE FROM atencion_servicios WHERE id_atencion = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar servicios de atención", e);
        }
    }
}
