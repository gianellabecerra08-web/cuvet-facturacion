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
        Mascota m = new Mascota();

        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setEspecie(rs.getString("especie"));
        m.setRaza(rs.getString("raza"));
        m.setSexo(rs.getString("sexo"));
        m.setEdadMeses(rs.getInt("edad_meses"));
        m.setIdCliente(rs.getInt("id_cliente"));

        return m;
    }
}