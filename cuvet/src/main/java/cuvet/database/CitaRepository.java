package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Cita;
import java.sql.*;
import java.util.*;

/**
 * CRUD de citas con listado por fecha y actualización de estado.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class CitaRepository implements IRepository<Cita, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();

    @Override
    public Cita guardar(Cita c) {
        String sql = "INSERT INTO citas (id_cliente, id_mascota, id_veterinario, fecha_hora, motivo, estado) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getIdCliente()); ps.setInt(2, c.getIdMascota());
            ps.setInt(3, c.getIdVeterinario()); ps.setTimestamp(4, Timestamp.valueOf(c.getFechaHora()));
            ps.setString(5, c.getMotivo()); ps.setString(6, c.getEstado().name());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) c.setId(rs.getInt(1)); }
            return c;
        } catch (SQLException e) { throw new DatabaseException("Error al guardar cita", e); }
    }

    @Override public Optional<Cita> buscarPorId(Integer id) { return Optional.empty(); }
    @Override public List<Cita> listarTodos() { return new ArrayList<>(); }
    @Override public void actualizar(Cita c) { actualizarEstado(c.getId(), c.getEstado()); }
    @Override public void eliminar(Integer id) { actualizarEstado(id, Cita.EstadoCita.CANCELADA); }

    public void actualizarEstado(int id, Cita.EstadoCita estado) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE citas SET estado=? WHERE id=?")) {
            ps.setString(1, estado.name()); ps.setInt(2, id); ps.executeUpdate();
        } catch (SQLException e) { throw new DatabaseException("Error al actualizar estado de cita", e); }
    }

    public List<Cita> listarPorFecha(java.time.LocalDate fecha) {
        List<Cita> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM citas WHERE DATE(fecha_hora)=? ORDER BY fecha_hora")) {
            ps.setDate(1, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cita c = new Cita();
                    c.setId(rs.getInt("id")); c.setIdCliente(rs.getInt("id_cliente"));
                    c.setIdMascota(rs.getInt("id_mascota")); c.setIdVeterinario(rs.getInt("id_veterinario"));
                    c.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                    c.setMotivo(rs.getString("motivo")); c.setEstado(Cita.EstadoCita.valueOf(rs.getString("estado")));
                    lista.add(c);
                }
            }
        } catch (SQLException e) { throw new DatabaseException("Error al listar citas por fecha", e); }
        return lista;
    }
}
