package cuvet.database;

import cuvet.model.Factura;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Factura sin ítems.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class FacturaRowMapper implements RowMapper<Factura> {
    @Override
    public Factura mapRow(ResultSet rs) throws SQLException {
        Factura f = new Factura();
        f.setId(rs.getInt("id"));
        f.setIdAtencion(rs.getInt("id_atencion"));
        f.setNumero(rs.getString("numero"));
        f.setFecha(rs.getDate("fecha").toLocalDate());
        f.setEstado(rs.getString("estado"));
        return f;
    }
}

