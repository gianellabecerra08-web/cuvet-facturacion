package cuvet.database;

import cuvet.model.Vacuna;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Vacuna.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class VacunaRowMapper implements RowMapper<Vacuna> {
    @Override
    public Vacuna mapRow(ResultSet rs) throws SQLException {
        Vacuna v = new Vacuna();
        v.setId(rs.getInt("id"));
        v.setIdMascota(rs.getInt("id_mascota"));
        v.setNombreVacuna(rs.getString("nombre_vacuna"));
        v.setLote(rs.getString("lote"));
        v.setFechaAplicacion(rs.getDate("fecha_aplicacion").toLocalDate());
        if (rs.getDate("fecha_proxima") != null) {
            v.setFechaProxima(rs.getDate("fecha_proxima").toLocalDate());
        }
        v.setIdVeterinario(rs.getInt("id_veterinario"));
        return v;
    }
}
