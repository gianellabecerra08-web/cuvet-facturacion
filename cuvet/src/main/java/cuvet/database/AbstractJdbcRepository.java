package cuvet.database;

import java.sql.Connection;

/**
 * Clase base para repositorios JDBC con acceso centralizado a la conexión.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public abstract class AbstractJdbcRepository {
    protected Connection getConnection() {
        return DatabaseConnection.getInstancia().getConnection();
    }
}