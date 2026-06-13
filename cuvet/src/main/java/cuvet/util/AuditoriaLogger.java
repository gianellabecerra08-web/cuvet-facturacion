package cuvet.util;

import cuvet.database.LogAuditoriaRepository;
import cuvet.model.LogAuditoria;

/**
 * Singleton que centraliza el registro de acciones (INSERT, UPDATE, DELETE, LOGIN).
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class AuditoriaLogger {
    private static volatile AuditoriaLogger instancia;
    private LogAuditoriaRepository logRepo;

    private AuditoriaLogger() {
        try { this.logRepo = new LogAuditoriaRepository(); }
        catch (Exception e) { System.err.println("[AUDITORIA] No disponible: " + e.getMessage()); }
    }

    public static AuditoriaLogger getInstancia() {
        if (instancia == null) {
            synchronized (AuditoriaLogger.class) {
                if (instancia == null) instancia = new AuditoriaLogger();
            }
        }
        return instancia;
    }

    public void registrar(int idUsuario, String accion, String tabla, int idRegistro, String detalle) {
        LogAuditoria log = new LogAuditoria(idUsuario, accion, tabla, idRegistro, detalle);
        System.out.println("[AUDITORIA] " + log);
        if (logRepo != null) {
            try { logRepo.guardar(log); } catch (Exception e) { System.err.println("[AUDITORIA] Error al persistir: " + e.getMessage()); }
        }
    }
}
