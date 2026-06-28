package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Factura;
import cuvet.model.ItemFactura;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de facturas e ítems. Provee listado por mes para reportes.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class FacturaRepository implements IRepository<Factura, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public Factura guardar(Factura f) {
        String sql = "INSERT INTO facturas (id_atencion, numero, fecha, subtotal, igv, total, estado) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, f.getIdAtencion());
            ps.setString(2, f.getNumero());
            ps.setDate(3, Date.valueOf(f.getFecha()));
            ps.setBigDecimal(4, f.getSubtotal());
            ps.setBigDecimal(5, f.getIgv());
            ps.setBigDecimal(6, f.getTotal());
            ps.setString(7, f.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    f.setId(rs.getInt(1));
                }
            }
            guardarItems(f);
            return f;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar factura", e);
        }
    }
    private void guardarItems(Factura f) throws SQLException {
        String sql = "INSERT INTO factura_items (id_factura, id_servicio, cantidad, precio_unitario, subtotal) VALUES (?,?,?,?,?)";
        for (ItemFactura item : f.getItems()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, f.getId());
                ps.setInt(2, item.getServicio().getId());
                ps.setInt(3, item.getCantidad());
                ps.setBigDecimal(4, item.getPrecioUnitario());
                ps.setBigDecimal(5, item.getSubtotal());
                ps.executeUpdate();
            }
        }
    }
    @Override
    public Optional<Factura> buscarPorId(Integer id) {
        String sql = "SELECT * FROM facturas WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar factura", e);
        }
        return Optional.empty();
    }
    public Optional<Factura> buscarPorAtencion(int idAtencion) {
        String sql = "SELECT * FROM facturas WHERE id_atencion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar factura por atención", e);
        }
        return Optional.empty();
    }
    public Optional<Factura> buscarPorNumero(String numero) {
        String sql = "SELECT * FROM facturas WHERE numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar factura por número", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Factura> listarTodos() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas ORDER BY fecha DESC, id DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar facturas", e);
        }
        return lista;
    }
    @Override
    public void actualizar(Factura f) {
        String sql = "UPDATE facturas SET estado=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getEstado());
            ps.setInt(2, f.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar factura", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        String sql = "UPDATE facturas SET estado='ANULADA' WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al anular factura", e);
        }
    }
    public List<Factura> listarPorMes(int mes, int anio) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas WHERE MONTH(fecha)=? AND YEAR(fecha)=? AND estado='EMITIDA' ORDER BY fecha";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar por mes", e);
        }
        return lista;
    }
    public String generarNumeroFactura() {
        String sql = "SELECT COUNT(*) FROM facturas";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                int siguiente = rs.getInt(1) + 1;
                return String.format("F001-%05d", siguiente);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al generar número de factura", e);
        }
        return "F001-00001";
    }
    public boolean existePorAtencion(int idAtencion) {
        String sql = "SELECT COUNT(*) FROM facturas WHERE id_atencion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al verificar factura por atención", e);
        }
        return false;
    }
    private Factura mapear(ResultSet rs) throws SQLException {
        Factura f = new Factura();
        f.setId(rs.getInt("id"));
        f.setIdAtencion(rs.getInt("id_atencion"));
        f.setNumero(rs.getString("numero"));
        f.setFecha(rs.getDate("fecha").toLocalDate());
        f.setEstado(rs.getString("estado"));
        f.setItems(cargarItems(f.getId()));
        return f;
    }
    private List<ItemFactura> cargarItems(int idFactura) {
        List<ItemFactura> items = new ArrayList<>();
        String sql = """
            SELECT fi.id, fi.id_factura, fi.cantidad, fi.precio_unitario, fi.subtotal,
                   s.id AS sid, s.tipo, s.descripcion, s.precio, s.activo
            FROM factura_items fi
            JOIN servicios s ON s.id = fi.id_servicio
            WHERE fi.id_factura = ?
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al cargar ítems de factura", e);
        }
        return items;
    }
}