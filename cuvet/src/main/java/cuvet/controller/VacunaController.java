package cuvet.controller;

import cuvet.database.VacunaRepository;
import cuvet.model.Vacuna;
import java.util.List;

/**
 * Registro del historial de vacunación por mascota.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class VacunaController {
    private final VacunaRepository vacunaRepo;

    public VacunaController() { this.vacunaRepo = new VacunaRepository(); }

    public Vacuna registrar(Vacuna vacuna) { return vacunaRepo.guardar(vacuna); }

    public List<Vacuna> listarPorMascota(int idMascota) { return vacunaRepo.listarPorMascota(idMascota); }
}
