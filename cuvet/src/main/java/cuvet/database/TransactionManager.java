package cuvet.database;

import cuvet.exception.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gestiona transacciones JDBC con commit/rollback automático.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class TransactionManager extends AbstractJdbcRepository {
    @FunctionalInterface
    public interface TransactionCallback {
        void execute(Connection conn) throws SQLException;
    }
    public void ejecutar(TransactionCallback callback) {
        Connection conn = getConnection();
        boolean autoCommitOriginal = true;
        try {
            autoCommitOriginal = conn.getAutoCommit();
            conn.setAutoCommit(false);
            callback.execute(conn);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new DatabaseException("Error al hacer rollback", ex);
            }
            throw new DatabaseException("Error en transacción", e);
        } finally {
            try {
                conn.setAutoCommit(autoCommitOriginal);
            } catch (SQLException e) {
                throw new DatabaseException("Error al restaurar autocommit", e);
            }
        }
    }
}
