package cuvet.database;

import cuvet.model.LogAuditoria;
import cuvet.util.AuditoriaLogger;

/**
 * Soporte reutilizable para registrar auditoría desde repositorios.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class AuditableRepositorySupport {
    private final LogAuditoriaRepository logRepo;
    private final AuditoriaLogger auditoriaLogger;
    public AuditableRepositorySupport() {
        this.logRepo = new LogAuditoriaRepository();
        this.auditoriaLogger = AuditoriaLogger.getInstancia();
    }
    public void registrarAccion(int idUsuario, AccionAuditoria accion, Tabla tabla, int idRegistro, String detalle) {
        LogAuditoria log = new LogAuditoria(
                idUsuario,
                accion.getValor(),
                tabla.getNombre(),
                idRegistro,
                detalle
        );
        logRepo.guardar(log);
        auditoriaLogger.registrar(idUsuario, accion.getValor(), tabla.getNombre(), idRegistro, detalle);
    }
    public void registrarInsert(int idUsuario, Tabla tabla, int idRegistro, String detalle) {
        registrarAccion(idUsuario, AccionAuditoria.INSERT, tabla, idRegistro, detalle);
    }
    public void registrarUpdate(int idUsuario, Tabla tabla, int idRegistro, String detalle) {
        registrarAccion(idUsuario, AccionAuditoria.UPDATE, tabla, idRegistro, detalle);
    }
    public void registrarDelete(int idUsuario, Tabla tabla, int idRegistro, String detalle) {
        registrarAccion(idUsuario, AccionAuditoria.DELETE, tabla, idRegistro, detalle);
    }
}
