package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Catálogo de servicios disponibles.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class ServicioRepository implements IRepository<Servicio, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public Servicio guardar(Servicio s) {
        String sql = "INSERT INTO servicios (tipo, descripcion, precio, activo) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getTipo().name());
            ps.setString(2, s.getDescripcion());
            ps.setBigDecimal(3, s.getPrecio());
            ps.setBoolean(4, s.isActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getInt(1));
                }
            }
            return s;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar servicio", e);
        }
    }
    @Override
    public Optional<Servicio> buscarPorId(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM servicios WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar servicio", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Servicio> listarTodos() {
        List<Servicio> lista = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM servicios WHERE activo=true ORDER BY tipo, descripcion")) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar servicios", e);
        }
        return lista;
    }
    @Override
    public void actualizar(Servicio s) {
        String sql = "UPDATE servicios SET descripcion=?, precio=?, activo=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getDescripcion());
            ps.setBigDecimal(2, s.getPrecio());
            ps.setBoolean(3, s.isActivo());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar servicio", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE servicios SET activo=false WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al desactivar servicio", e);
        }
    }
    public List<Servicio> listarPorTipo(TipoServicio tipo) {
        List<Servicio> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM servicios WHERE tipo=? AND activo=true ORDER BY descripcion")) {
            ps.setString(1, tipo.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar por tipo", e);
        }
        return lista;
    }
    private Servicio mapear(ResultSet rs) throws SQLException {
        Servicio s = new Servicio(
                rs.getInt("id"),
                TipoServicio.valueOf(rs.getString("tipo")),
                rs.getString("descripcion"),
                rs.getBigDecimal("precio")
        );
        s.setActivo(rs.getBoolean("activo"));
        return s;
    }
}