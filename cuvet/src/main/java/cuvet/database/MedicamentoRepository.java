package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Inventario de medicamentos con alertas de stock bajo.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MedicamentoRepository implements IRepository<Medicamento, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public Medicamento guardar(Medicamento m) {
        String sql = "INSERT INTO medicamentos (nombre, principio_activo, laboratorio, stock_actual, stock_minimo, precio_venta, activo) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getPrincipioActivo());
            ps.setString(3, m.getLaboratorio());
            ps.setInt(4, m.getStockActual());
            ps.setInt(5, m.getStockMinimo());
            ps.setBigDecimal(6, m.getPrecioVenta());
            ps.setBoolean(7, m.isActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                }
            }
            return m;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar medicamento", e);
        }
    }
    @Override
    public Optional<Medicamento> buscarPorId(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM medicamentos WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar medicamento", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Medicamento> listarTodos() {
        List<Medicamento> lista = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM medicamentos WHERE activo=true ORDER BY nombre")) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar medicamentos", e);
        }
        return lista;
    }
    @Override
    public void actualizar(Medicamento m) {
        String sql = "UPDATE medicamentos SET nombre=?, principio_activo=?, laboratorio=?, stock_actual=?, stock_minimo=?, precio_venta=?, activo=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getPrincipioActivo());
            ps.setString(3, m.getLaboratorio());
            ps.setInt(4, m.getStockActual());
            ps.setInt(5, m.getStockMinimo());
            ps.setBigDecimal(6, m.getPrecioVenta());
            ps.setBoolean(7, m.isActivo());
            ps.setInt(8, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar medicamento", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE medicamentos SET activo=false WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al desactivar medicamento", e);
        }
    }
    public List<Medicamento> listarStockBajo() {
        List<Medicamento> lista = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM medicamentos WHERE stock_actual <= stock_minimo AND activo=true ORDER BY stock_actual")) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar stock bajo", e);
        }
        return lista;
    }
    public void actualizarStock(int id, int nuevoStock) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE medicamentos SET stock_actual=? WHERE id=?")) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar stock", e);
        }
    }
    public Optional<Medicamento> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM medicamentos WHERE nombre = ? AND activo = true";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar medicamento por nombre", e);
        }
        return Optional.empty();
    }
    private Medicamento mapear(ResultSet rs) throws SQLException {
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