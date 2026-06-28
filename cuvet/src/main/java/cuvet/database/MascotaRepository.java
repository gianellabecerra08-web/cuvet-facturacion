package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de mascotas. Permite listar por cliente.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class MascotaRepository implements IRepository<Mascota, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public Mascota guardar(Mascota m) {
        String sql = "INSERT INTO mascotas (nombre, especie, raza, edad_meses, sexo, id_cliente) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setInt(4, m.getEdadMeses());
            ps.setString(5, m.getSexo());
            ps.setInt(6, m.getIdCliente());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                }
            }
            return m;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar mascota", e);
        }
    }
    @Override
    public Optional<Mascota> buscarPorId(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM mascotas WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar mascota", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Mascota> listarTodos() {
        List<Mascota> lista = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM mascotas ORDER BY nombre")) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar mascotas", e);
        }
        return lista;
    }
    @Override
    public void actualizar(Mascota m) {
        String sql = "UPDATE mascotas SET nombre=?, especie=?, raza=?, edad_meses=?, sexo=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setInt(4, m.getEdadMeses());
            ps.setString(5, m.getSexo());
            ps.setInt(6, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar mascota", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM mascotas WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar mascota", e);
        }
    }
    public List<Mascota> listarPorCliente(int idCliente) {
        List<Mascota> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM mascotas WHERE id_cliente=? ORDER BY nombre")) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar mascotas por cliente", e);
        }
        return lista;
    }
    public List<Mascota> buscarPorNombre(String nombre) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE nombre LIKE ? ORDER BY nombre";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar mascota por nombre", e);
        }
        return lista;
    }
    public int contarPorCliente(int idCliente) {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE id_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al contar mascotas por cliente", e);
        }
        return 0;
    }
    private Mascota mapear(ResultSet rs) throws SQLException {
        return new Mascota(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("especie"),
                rs.getString("raza"),
                rs.getInt("edad_meses"),
                rs.getString("sexo"),
                rs.getInt("id_cliente")
        );
    }
}