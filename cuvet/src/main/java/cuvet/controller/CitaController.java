package cuvet.controller;

import cuvet.database.CitaRepository;
import cuvet.model.Cita;
import java.time.LocalDate;
import java.util.List;

/**
 * Gestión de citas: agendamiento, confirmación, cancelación y marcado como atendida.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class CitaController {
    private final CitaRepository citaRepo;

    public CitaController() { this.citaRepo = new CitaRepository(); }

    public Cita agendar(Cita cita) { citaRepo.guardar(cita); return cita; }

    public void confirmar(int idCita) { citaRepo.actualizarEstado(idCita, Cita.EstadoCita.CONFIRMADA); }

    public void marcarAtendida(int idCita) { citaRepo.actualizarEstado(idCita, Cita.EstadoCita.ATENDIDA); }

    public void cancelar(int idCita) { citaRepo.actualizarEstado(idCita, Cita.EstadoCita.CANCELADA); }

    public List<Cita> listarPorFecha(LocalDate fecha) { return citaRepo.listarPorFecha(fecha); }

    public List<Cita> listarHoy() { return listarPorFecha(LocalDate.now()); }
}
