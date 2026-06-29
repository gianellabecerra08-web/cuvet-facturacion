package cuvet.database;

import cuvet.model.ItemFactura;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad ItemFactura con servicio embebido.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ItemFacturaRowMapper implements RowMapper<ItemFactura> {
    @Override
    public ItemFactura mapRow(ResultSet rs) throws SQLException {
        Servicio servicio = new Servicio(
                rs.getInt("sid"),
                TipoServicio.valueOf(rs.getString("tipo")),
                rs.getString("descripcion"),
                rs.getBigDecimal("precio")
        );
        servicio.setActivo(rs.getBoolean("activo"));
        ItemFactura item = new ItemFactura();
        item.setId(rs.getInt("id"));
        item.setIdFactura(rs.getInt("id_factura"));
        item.setServicio(servicio);
        item.setCantidad(rs.getInt("cantidad"));
        item.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
        return item;
    }
}
