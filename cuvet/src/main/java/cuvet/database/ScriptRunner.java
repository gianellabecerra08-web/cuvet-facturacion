package cuvet.database;

import cuvet.exception.DatabaseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ejecuta scripts SQL desde archivo externo.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class ScriptRunner extends AbstractJdbcRepository {
    public void ejecutarArchivo(Path rutaScript) {
        if (rutaScript == null || !Files.exists(rutaScript)) {
            throw new DatabaseException("El archivo SQL no existe: " + rutaScript);
        }
        StringBuilder script = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(rutaScript, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String trim = linea.trim();
                if (trim.isEmpty() || trim.startsWith("--")) {
                    continue;
                }
                script.append(linea).append('\n');
            }
        } catch (IOException e) {
            throw new DatabaseException("Error al leer script SQL", e);
        }
        ejecutarScript(script.toString());
    }
    public void ejecutarScript(String script) {
        Connection conn = getConnection();
        String[] sentencias = script.split(";");
        try (Statement st = conn.createStatement()) {
            for (String sentencia : sentencias) {
                String sql = sentencia.trim();
                if (!sql.isEmpty()) {
                    st.execute(sql);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al ejecutar script SQL", e);
        }
    }
}
