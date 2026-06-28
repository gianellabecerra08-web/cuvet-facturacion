package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.LogAuditoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Persistencia de logs de auditoría (solo INSERT y consultas — inmutable).
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class LogAuditoriaRepository implements IRepository<LogAuditoria, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public LogAuditoria guardar(LogAuditoria log) {
        String sql = "INSERT INTO log_auditoria (id_usuario, accion, tabla, id_registro, timestamp, detalle) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, log.getIdUsuario());
            ps.setString(2, log.getAccion());
            ps.setString(3, log.getTabla());
            ps.setInt(4, log.getIdRegistro());
            ps.setTimestamp(5, Timestamp.valueOf(log.getTimestamp()));
            ps.setString(6, log.getDetalle());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    log.setId(rs.getInt(1));
                }
            }
            return log;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar log", e);
        }
    }
    @Override
    public Optional<LogAuditoria> buscarPorId(Integer id) {
        String sql = "SELECT * FROM log_auditoria WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar log", e);
        }
        return Optional.empty();
    }
    @Override
    public List<LogAuditoria> listarTodos() {
        List<LogAuditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM log_auditoria ORDER BY timestamp DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar logs", e);
        }
        return lista;
    }
    @Override
    public void actualizar(LogAuditoria e) {
        throw new UnsupportedOperationException("Log es inmutable");
    }
    @Override
    public void eliminar(Integer id) {
        throw new UnsupportedOperationException("Log es inmutable");
    }
    public List<LogAuditoria> listarPorUsuario(int idUsuario) {
        List<LogAuditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM log_auditoria WHERE id_usuario = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar logs por usuario", e);
        }
        return lista;
    }
    public List<LogAuditoria> listarPorTabla(String tabla) {
        List<LogAuditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM log_auditoria WHERE tabla = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tabla);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar logs por tabla", e);
        }
        return lista;
    }
    public List<LogAuditoria> listarPorFecha(LocalDate fecha) {
        List<LogAuditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM log_auditoria WHERE DATE(timestamp) = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar logs por fecha", e);
        }
        return lista;
    }
    private LogAuditoria mapear(ResultSet rs) throws SQLException {
        LogAuditoria log = new LogAuditoria(
                rs.getInt("id_usuario"),
                rs.getString("accion"),
                rs.getString("tabla"),
                rs.getInt("id_registro"),
                rs.getString("detalle")
        );
        log.setId(rs.getInt("id"));
        return log;
    }
}