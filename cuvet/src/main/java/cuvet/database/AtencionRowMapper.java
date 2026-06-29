package cuvet.database;

import cuvet.model.Atencion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Atencion.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class AtencionRowMapper implements RowMapper<Atencion> {
    @Override
    public Atencion mapRow(ResultSet rs) throws SQLException {
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
