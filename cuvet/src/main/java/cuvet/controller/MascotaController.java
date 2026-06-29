package cuvet.controller;

import cuvet.database.MascotaRepository;
import cuvet.model.Mascota;
import cuvet.util.AuditoriaLogger;
import cuvet.util.SessionManager;
import java.util.List;

public class MascotaController {

    private final MascotaRepository mascotaRepo;
    private final AuditoriaLogger auditoria;

    public MascotaController() {
        this.mascotaRepo = new MascotaRepository();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    public Mascota registrar(Mascota m) {
        mascotaRepo.guardar(m);
        log("INSERT", "mascotas", m.getId(), "Mascota registrada: " + m.getNombre());
        return m;
    }

    public List<Mascota> listarTodos() {
        return mascotaRepo.listarTodos();
    }

    public List<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepo.buscarPorNombre(nombre);
    }

    private void log(String accion, String tabla, int id, String detalle) {
        int uid = SessionManager.getInstancia().getUsuarioActual() != null ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(uid, accion, tabla, id, detalle);
    }
}
