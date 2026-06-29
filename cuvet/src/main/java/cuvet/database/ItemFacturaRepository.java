package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.ItemFactura;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio especializado para ítems de factura.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ItemFacturaRepository extends AbstractJdbcRepository implements IRepository<ItemFactura, Integer> {
    @Override
    public ItemFactura guardar(ItemFactura item) {
        String sql = "INSERT INTO factura_items (id_factura, id_servicio, cantidad, precio_unitario, subtotal) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, item.getIdFactura());
            ps.setInt(2, item.getServicio().getId());
            ps.setInt(3, item.getCantidad());
            ps.setBigDecimal(4, item.getPrecioUnitario());
            ps.setBigDecimal(5, item.getSubtotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    item.setId(rs.getInt(1));
                }
            }
            return item;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar ítem de factura", e);
        }
    }
    @Override
    public Optional<ItemFactura> buscarPorId(Integer id) {
        String sql = """
            SELECT fi.*, s.id AS sid, s.tipo, s.descripcion, s.precio, s.activo
            FROM factura_items fi
            JOIN servicios s ON s.id = fi.id_servicio
            WHERE fi.id = ?
            """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar ítem de factura", e);
        }
        return Optional.empty();
    }
    @Override
    public List<ItemFactura> listarTodos() {
        String sql = """
            SELECT fi.*, s.id AS sid, s.tipo, s.descripcion, s.precio, s.activo
            FROM factura_items fi
            JOIN servicios s ON s.id = fi.id_servicio
            ORDER BY fi.id DESC
            """;
        List<ItemFactura> lista = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar ítems de factura", e);
        }
        return lista;
    }
    @Override
    public void actualizar(ItemFactura item) {
        String sql = "UPDATE factura_items SET cantidad=?, precio_unitario=?, subtotal=? WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, item.getCantidad());
            ps.setBigDecimal(2, item.getPrecioUnitario());
            ps.setBigDecimal(3, item.getSubtotal());
            ps.setInt(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar ítem de factura", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM factura_items WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar ítem de factura", e);
        }
    }
    public List<ItemFactura> listarPorFactura(int idFactura) {
        String sql = """
            SELECT fi.*, s.id AS sid, s.tipo, s.descripcion, s.precio, s.activo
            FROM factura_items fi
            JOIN servicios s ON s.id = fi.id_servicio
            WHERE fi.id_factura = ?
            """;
        List<ItemFactura> lista = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar ítems por factura", e);
        }
        return lista;
    }
    private ItemFactura mapear(ResultSet rs) throws SQLException {
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
