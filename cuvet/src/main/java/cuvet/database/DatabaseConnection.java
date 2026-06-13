package cuvet.database;

import cuvet.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton que gestiona la conexión JDBC a MySQL.
 * Patrón Singleton con doble verificación (thread-safe).
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class DatabaseConnection {

    private static final String URL  = "jdbc:mysql://localhost:3306/cuvet_db?useSSL=false&serverTimezone=America/Lima&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "cuvet2026";

    private static volatile DatabaseConnection instancia;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Driver MySQL no encontrado", e);
        } catch (SQLException e) {
            throw new DatabaseException("No se pudo conectar a la base de datos: " + e.getMessage(), e);
        }
    }

    public static DatabaseConnection getInstancia() {
        if (instancia == null) {
            synchronized (DatabaseConnection.class) {
                if (instancia == null) {
                    instancia = new DatabaseConnection();
                }
            }
        }
        return instancia;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al verificar conexión", e);
        }
        return connection;
    }

    public void cerrar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al cerrar conexión", e);
        }
    }
}
