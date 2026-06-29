package cuvet.controller;

import cuvet.database.ServicioRepository;
import cuvet.exception.ServicioNotFoundException;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import cuvet.util.AuditoriaLogger;
import cuvet.util.SessionManager;
import java.util.List;

/**
 * Gestiona el catálogo de servicios activos disponibles para facturación.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class ServicioController {
    private final ServicioRepository servicioRepo;
    private final AuditoriaLogger auditoria;

    public ServicioController() {
        this.servicioRepo = new ServicioRepository();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    public Servicio registrar(Servicio s) { servicioRepo.guardar(s); return s; }

    public Servicio buscarPorId(int id) {
        return servicioRepo.buscarPorId(id).orElseThrow(() -> new ServicioNotFoundException(String.valueOf(id)));
    }

    public List<Servicio> listarActivos() { return servicioRepo.listarTodos(); }

    public List<Servicio> listarPorTipo(TipoServicio tipo) { return servicioRepo.listarPorTipo(tipo); }

    public void actualizar(Servicio s) { servicioRepo.actualizar(s); }

    public void desactivar(int id) {
        Servicio s = buscarPorId(id);
        s.setActivo(false);
        servicioRepo.actualizar(s);
        int uid = SessionManager.getInstancia().getUsuarioActual() != null ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(uid, "DELETE", "servicios", id, "Servicio desactivado: " + s.getDescripcion());
    }
}
