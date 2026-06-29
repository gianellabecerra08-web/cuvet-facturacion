package cuvet.controller;

import cuvet.database.AtencionRepository;
import cuvet.database.ServicioRepository;
import cuvet.exception.DuplicadoException;
import cuvet.exception.ServicioNotFoundException;
import cuvet.model.Atencion;
import cuvet.model.Servicio;
import cuvet.model.TipoServicio;
import cuvet.util.AuditoriaLogger;
import cuvet.util.SessionManager;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador de atenciones. Coordina el registro de atenciones y
 * valida duplicados antes de persistir (CU01 — flujo principal).
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class AtencionController {

    private final AtencionRepository atencionRepo;
    private final ServicioRepository servicioRepo;
    private final FacturaController facturaController;
    private final AuditoriaLogger auditoria;

    public AtencionController() {
        this.atencionRepo = new AtencionRepository();
        this.servicioRepo = new ServicioRepository();
        this.facturaController = new FacturaController();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    /**
     * Registra una atención y genera la factura correspondiente.
     * Implementa el flujo principal del CU01.
     *
     * @param idCliente    ID del cliente propietario
     * @param idMascota    ID de la mascota atendida
     * @param idVeterinario ID del veterinario tratante
     * @param idsServicios  IDs de los servicios a facturar
     * @param observaciones notas clínicas opcionales
     * @return Atencion persistida con su factura adjunta
     * @throws DuplicadoException si algún servicio ya fue registrado para la misma mascota hoy
     */
    public Atencion registrarAtencion(int idCliente, int idMascota, int idVeterinario,
                                      List<Integer> idsServicios, String observaciones) {
        // Paso 1: Cargar servicios
        List<Servicio> servicios = idsServicios.stream()
                .map(id -> servicioRepo.buscarPorId(id)
                        .orElseThrow(() -> new ServicioNotFoundException(String.valueOf(id))))
                .toList();

        // Paso 2: Validar duplicados (RF03)
        for (Servicio s : servicios) {
            verificarDuplicado(idMascota, s.getTipo(), LocalDate.now());
        }

        // Paso 3: Construir y persistir atención
        Atencion atencion = new Atencion(idCliente, idMascota, idVeterinario, observaciones);
        servicios.forEach(atencion::agregarServicio);
        atencionRepo.guardar(atencion);

        // Paso 4: Generar factura automáticamente
        facturaController.generarFactura(atencion);

        int idUsuario = SessionManager.getInstancia().getUsuarioActual() != null
                ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(idUsuario, "INSERT", "atenciones", atencion.getId(),
                "Atención registrada. Mascota: " + idMascota + ", servicios: " + servicios.size());

        return atencion;
    }

    /**
     * Verifica duplicado. Lanza DuplicadoException si ya existe.
     */
    private void verificarDuplicado(int idMascota, TipoServicio tipo, LocalDate fecha) {
        if (atencionRepo.existeDuplicado(idMascota, tipo, fecha)) {
            throw new DuplicadoException(
                "El servicio '" + tipo.getDescripcion() + "' ya fue registrado para esta mascota hoy (" + fecha + ").");
        }
    }

    public List<Atencion> listarPorMascota(int idMascota) {
        return atencionRepo.listarPorMascota(idMascota);
    }

    public List<Atencion> listarTodas() {
        return atencionRepo.listarTodos();
    }
}
