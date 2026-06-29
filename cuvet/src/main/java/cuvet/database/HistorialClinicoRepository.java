package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.HistorialClinico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de historial clínico construido desde atenciones.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class HistorialClinicoRepository extends AbstractJdbcRepository implements IRepository<HistorialClinico, Integer> {
    @Override
    public HistorialClinico guardar(HistorialClinico h) {
        throw new UnsupportedOperationException("Use AtencionRepository para registrar atenciones");
    }
    @Override
    public Optional<HistorialClinico> buscarPorId(Integer id) {
        return buscarPorAtencion(id);
    }
    @Override
    public List<HistorialClinico> listarTodos() {
        List<HistorialClinico> lista = new ArrayList<>();
        String sql = "SELECT * FROM atenciones ORDER BY fecha DESC";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar historial clínico", e);
        }
        return lista;
    }
    @Override
    public void actualizar(HistorialClinico h) {
        throw new UnsupportedOperationException("Use AtencionRepository para actualizar observaciones");
    }
    @Override
    public void eliminar(Integer id) {
        throw new UnsupportedOperationException("Use AtencionRepository para eliminar atenciones");
    }
    public List<HistorialClinico> listarPorMascota(int idMascota) {
        List<HistorialClinico> lista = new ArrayList<>();
        String sql = "SELECT * FROM atenciones WHERE id_mascota = ? ORDER BY fecha DESC";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar historial por mascota", e);
        }
        return lista;
    }
    public Optional<HistorialClinico> buscarPorAtencion(int idAtencion) {
        String sql = "SELECT * FROM atenciones WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar historial por atención", e);
        }
        return Optional.empty();
    }
    private HistorialClinico mapear(ResultSet rs) throws SQLException {
        HistorialClinico h = new HistorialClinico();
        h.setId(rs.getInt("id"));
        h.setIdAtencion(rs.getInt("id"));
        h.setIdMascota(rs.getInt("id_mascota"));
        h.setFecha(rs.getDate("fecha").toLocalDate());
        h.setObservaciones(rs.getString("observaciones"));
        h.setDiagnostico("Atención #" + rs.getInt("id"));
        h.setTratamiento("Servicios registrados en atención");
        return h;
    }
}