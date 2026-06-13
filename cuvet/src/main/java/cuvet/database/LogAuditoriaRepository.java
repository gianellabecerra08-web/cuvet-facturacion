package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.LogAuditoria;
import java.sql.*;
import java.util.*;

/**
 * Persistencia de logs de auditoría (solo INSERT — inmutable).
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class LogAuditoriaRepository implements IRepository<LogAuditoria, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();

    @Override
    public LogAuditoria guardar(LogAuditoria log) {
        String sql = "INSERT INTO log_auditoria (id_usuario, accion, tabla, id_registro, timestamp, detalle) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getIdUsuario()); ps.setString(2, log.getAccion());
            ps.setString(3, log.getTabla()); ps.setInt(4, log.getIdRegistro());
            ps.setTimestamp(5, Timestamp.valueOf(log.getTimestamp())); ps.setString(6, log.getDetalle());
            ps.executeUpdate();
            return log;
        } catch (SQLException e) { throw new DatabaseException("Error al guardar log", e); }
    }

    @Override public Optional<LogAuditoria> buscarPorId(Integer id) { return Optional.empty(); }
    @Override public List<LogAuditoria> listarTodos() { return new ArrayList<>(); }
    @Override public void actualizar(LogAuditoria e) { throw new UnsupportedOperationException("Log es inmutable"); }
    @Override public void eliminar(Integer id) { throw new UnsupportedOperationException("Log es inmutable"); }
}
