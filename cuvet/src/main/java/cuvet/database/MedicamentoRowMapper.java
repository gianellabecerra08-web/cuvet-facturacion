package cuvet.database;

import cuvet.model.Medicamento;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad Medicamento.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MedicamentoRowMapper implements RowMapper<Medicamento> {
    @Override
    public Medicamento mapRow(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("principio_activo"),
                rs.getString("laboratorio"),
                rs.getInt("stock_actual"),
                rs.getInt("stock_minimo"),
                rs.getBigDecimal("precio_venta")
        );
        m.setActivo(rs.getBoolean("activo"));
        return m;
    }
}
