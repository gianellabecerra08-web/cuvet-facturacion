package cuvet.database;

import cuvet.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Cliente.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ClienteRowMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("telefono"),
                rs.getString("email")
        );
    }
}
