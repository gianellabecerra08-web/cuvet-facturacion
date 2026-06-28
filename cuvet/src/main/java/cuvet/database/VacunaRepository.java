package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Vacuna;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de vacunas. Historial por mascota.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class VacunaRepository implements IRepository<Vacuna, Integer> {
    private final Connection conn = DatabaseConnection.getInstancia().getConnection();
    @Override
    public Vacuna guardar(Vacuna v) {
        String sql = "INSERT INTO vacunas (id_mascota, nombre_vacuna, lote, fecha_aplicacion, fecha_proxima, id_veterinario) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getIdMascota());
            ps.setString(2, v.getNombreVacuna());
            ps.setString(3, v.getLote());
            ps.setDate(4, Date.valueOf(v.getFechaAplicacion()));
            if (v.getFechaProxima() != null) {
                ps.setDate(5, Date.valueOf(v.getFechaProxima()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            ps.setInt(6, v.getIdVeterinario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }
            return v;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar vacuna", e);
        }
    }
    @Override
    public Optional<Vacuna> buscarPorId(Integer id) {
        String sql = "SELECT * FROM vacunas WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al buscar vacuna", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Vacuna> listarTodos() {
        List<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM vacunas ORDER BY fecha_aplicacion DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar vacunas", e);
        }
        return lista;
    }
    @Override
    public void actualizar(Vacuna v) {
        String sql = "UPDATE vacunas SET nombre_vacuna=?, lote=?, fecha_aplicacion=?, fecha_proxima=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getNombreVacuna());
            ps.setString(2, v.getLote());
            ps.setDate(3, Date.valueOf(v.getFechaAplicacion()));
            if (v.getFechaProxima() != null) {
                ps.setDate(4, Date.valueOf(v.getFechaProxima()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setInt(5, v.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar vacuna", e);
        }
    }
    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM vacunas WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar vacuna", e);
        }
    }
    public List<Vacuna> listarPorMascota(int idMascota) {
        List<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM vacunas WHERE id_mascota=? ORDER BY fecha_aplicacion DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar vacunas por mascota", e);
        }
        return lista;
    }
    public List<Vacuna> listarProximasVencer(LocalDate hasta) {
        List<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM vacunas WHERE fecha_proxima IS NOT NULL AND fecha_proxima <= ? ORDER BY fecha_proxima";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(hasta));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar vacunas próximas a vencer", e);
        }
        return lista;
    }
    private Vacuna mapear(ResultSet rs) throws SQLException {
        Vacuna v = new Vacuna();
        v.setId(rs.getInt("id"));
        v.setIdMascota(rs.getInt("id_mascota"));
        v.setNombreVacuna(rs.getString("nombre_vacuna"));
        v.setLote(rs.getString("lote"));
        v.setFechaAplicacion(rs.getDate("fecha_aplicacion").toLocalDate());
        if (rs.getDate("fecha_proxima") != null) {
            v.setFechaProxima(rs.getDate("fecha_proxima").toLocalDate());
        }
        v.setIdVeterinario(rs.getInt("id_veterinario"));
        return v;
    }
}