package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Atencion;
import cuvet.model.TipoServicio;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de atenciones. Implementa validación de duplicados.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class AtencionRepository implements IRepository<Atencion, Integer> {

    private final Connection conn = DatabaseConnection.getInstancia().getConnection();

    @Override
    public Atencion guardar(Atencion a) {
        String sql = "INSERT INTO atenciones (fecha, id_cliente, id_mascota, id_veterinario, observaciones) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(a.getFecha()));
            ps.setInt(2, a.getIdCliente());
            ps.setInt(3, a.getIdMascota());
            ps.setInt(4, a.getIdVeterinario());
            ps.setString(5, a.getObservaciones());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getInt(1));
            }
            return a;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar atención", e);
        }
    }

    @Override
    public Optional<Atencion> buscarPorId(Integer id) {
        String sql = "SELECT * FROM atenciones WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar atención", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Atencion> listarTodos() {
        List<Atencion> lista = new ArrayList<>();
        String sql = "SELECT * FROM atenciones ORDER BY fecha DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar atenciones", e);
        }
        return lista;
    }

    @Override
    public void actualizar(Atencion a) {
        String sql = "UPDATE atenciones SET observaciones=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getObservaciones());
            ps.setInt(2, a.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar atención", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM atenciones WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar atención", e);
        }
    }

    /**
     * Verifica si ya existe una atención del mismo tipo de servicio
     * para la misma mascota en la misma fecha. Previene cobros duplicados (RF03).
     */
    public boolean existeDuplicado(int idMascota, TipoServicio tipo, LocalDate fecha) {
        String sql = """
            SELECT COUNT(*) FROM atenciones a
            JOIN atencion_servicios ats ON ats.id_atencion = a.id
            JOIN servicios s ON s.id = ats.id_servicio
            WHERE a.id_mascota = ? AND s.tipo = ? AND a.fecha = ?
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            ps.setString(2, tipo.name());
            ps.setDate(3, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al verificar duplicado", e);
        }
        return false;
    }

    /** Lista atenciones por mascota */
    public List<Atencion> listarPorMascota(int idMascota) {
        List<Atencion> lista = new ArrayList<>();
        String sql = "SELECT * FROM atenciones WHERE id_mascota = ? ORDER BY fecha DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar por mascota", e);
        }
        return lista;
    }

    private Atencion mapear(ResultSet rs) throws SQLException {
        Atencion a = new Atencion();
        a.setId(rs.getInt("id"));
        a.setFecha(rs.getDate("fecha").toLocalDate());
        a.setIdCliente(rs.getInt("id_cliente"));
        a.setIdMascota(rs.getInt("id_mascota"));
        a.setIdVeterinario(rs.getInt("id_veterinario"));
        a.setObservaciones(rs.getString("observaciones"));
        return a;
    }
}
