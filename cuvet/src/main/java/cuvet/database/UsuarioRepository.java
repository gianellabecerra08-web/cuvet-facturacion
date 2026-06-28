package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Rol;
import cuvet.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de usuarios. Valida credenciales para autenticación.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */
public class UsuarioRepository implements IRepository<Usuario, Integer> {

    private final Connection conn = DatabaseConnection.getInstancia().getConnection();

    @Override
    public Usuario guardar(Usuario u) {
        String sql = "INSERT INTO usuarios (username, password_hash, nombre, rol, activo) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getRol().name());
            ps.setBoolean(5, u.isActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setId(rs.getInt(1));
            }
            return u;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar usuario", e);
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar usuario", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE activo = true";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar usuarios", e);
        }
        return lista;
    }

    @Override
    public void actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, rol=?, activo=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getRol().name());
            ps.setBoolean(3, u.isActivo());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar usuario", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "UPDATE usuarios SET activo=false WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al desactivar usuario", e);
        }
    }

    /**
     * Autentica usuario por username y passwordHash (SHA-256+salt).
     * @return Optional con el usuario si las credenciales son válidas
     */
    public Optional<Usuario> autenticar(String username, String passwordHash) {
        String sql = "SELECT * FROM usuarios WHERE username=? AND password_hash=? AND activo=true";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error en autenticación", e);
        }
        return Optional.empty();
    }

    public boolean existeUsername(String username) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al verificar username", e);
        }
        return false;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("nombre"),
                Rol.valueOf(rs.getString("rol"))
        );
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }
}
