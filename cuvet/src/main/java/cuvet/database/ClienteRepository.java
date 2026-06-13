package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CRUD completo de clientes con búsqueda por nombre y DNI.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class ClienteRepository implements IRepository<Cliente, Integer> {

    private final Connection conn = DatabaseConnection.getInstancia().getConnection();

    @Override
    public Cliente guardar(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, apellido, dni, telefono, email) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
            return c;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar cliente por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY apellido, nombre";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar clientes", e);
        }
        return lista;
    }

    @Override
    public void actualizar(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, apellido=?, dni=?, telefono=?, email=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar cliente", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar cliente", e);
        }
    }

    /** Búsqueda por nombre o apellido (LIKE) */
    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ? OR apellido LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String patron = "%" + nombre + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar por nombre", e);
        }
        return lista;
    }

    /** Búsqueda exacta por DNI */
    public Optional<Cliente> buscarPorDni(String dni) {
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar por DNI", e);
        }
        return Optional.empty();
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("telefono"),
                rs.getString("email")
        );
    }
}
