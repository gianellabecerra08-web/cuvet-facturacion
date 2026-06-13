package cuvet.database;

import cuvet.exception.DatabaseException;
import cuvet.model.Vacuna;
import java.sql.*;
import java.util.*;

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
            ps.setInt(1, v.getIdMascota()); ps.setString(2, v.getNombreVacuna());
            ps.setString(3, v.getLote()); ps.setDate(4, Date.valueOf(v.getFechaAplicacion()));
            ps.setDate(5, v.getFechaProxima() != null ? Date.valueOf(v.getFechaProxima()) : null);
            ps.setInt(6, v.getIdVeterinario()); ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) v.setId(rs.getInt(1)); }
            return v;
        } catch (SQLException e) { throw new DatabaseException("Error al guardar vacuna", e); }
    }

    @Override public Optional<Vacuna> buscarPorId(Integer id) { return Optional.empty(); }
    @Override public List<Vacuna> listarTodos() { return new ArrayList<>(); }
    @Override public void actualizar(Vacuna v) {}
    @Override public void eliminar(Integer id) {}

    public List<Vacuna> listarPorMascota(int idMascota) {
        List<Vacuna> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM vacunas WHERE id_mascota=? ORDER BY fecha_aplicacion DESC")) {
            ps.setInt(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vacuna v = new Vacuna();
                    v.setId(rs.getInt("id")); v.setIdMascota(rs.getInt("id_mascota"));
                    v.setNombreVacuna(rs.getString("nombre_vacuna")); v.setLote(rs.getString("lote"));
                    v.setFechaAplicacion(rs.getDate("fecha_aplicacion").toLocalDate());
                    if (rs.getDate("fecha_proxima") != null) v.setFechaProxima(rs.getDate("fecha_proxima").toLocalDate());
                    lista.add(v);
                }
            }
        } catch (SQLException e) { throw new DatabaseException("Error al listar vacunas", e); }
        return lista;
    }
}
