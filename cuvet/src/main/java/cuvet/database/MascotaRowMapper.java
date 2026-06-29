package cuvet.database;

import cuvet.model.Mascota;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Mascota.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MascotaRowMapper implements RowMapper<Mascota> {
    @Override
    public Mascota mapRow(ResultSet rs) throws SQLException {
        return new Mascota(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("especie"),
                rs.getString("raza"),
                rs.getInt("edad_meses"),
                rs.getString("sexo"),
                rs.getInt("id_cliente")
        );
    }
}
