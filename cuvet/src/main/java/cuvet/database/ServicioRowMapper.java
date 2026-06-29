package cuvet.database;

import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Servicio.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ServicioRowMapper implements RowMapper<Servicio> {
    @Override
    public Servicio mapRow(ResultSet rs) throws SQLException {
        Servicio s = new Servicio(
                rs.getInt("id"),
                TipoServicio.valueOf(rs.getString("tipo")),
                rs.getString("descripcion"),
                rs.getBigDecimal("precio")
        );
        s.setActivo(rs.getBoolean("activo"));
        return s;
    }
}
