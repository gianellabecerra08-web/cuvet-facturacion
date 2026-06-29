package cuvet.database;

import cuvet.model.Cita;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Cita.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class CitaRowMapper implements RowMapper<Cita> {
    @Override
    public Cita mapRow(ResultSet rs) throws SQLException {
        Cita c = new Cita();
        c.setId(rs.getInt("id"));
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setIdMascota(rs.getInt("id_mascota"));
        c.setIdVeterinario(rs.getInt("id_veterinario"));
        c.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        c.setMotivo(rs.getString("motivo"));
        c.setEstado(Cita.EstadoCita.valueOf(rs.getString("estado")));
        return c;
    }
}