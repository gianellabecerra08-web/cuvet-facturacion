package cuvet.controller;

import cuvet.database.ClienteRepository;
import cuvet.database.MascotaRepository;
import cuvet.exception.ClienteNotFoundException;
import cuvet.model.Cliente;
import cuvet.model.Mascota;
import cuvet.util.AuditoriaLogger;
import cuvet.util.SessionManager;
import cuvet.util.ValidadorDNI;
import java.util.List;

/**
 * Gestiona el CRUD de clientes y asociación con mascotas.
 * @author Lucero Silva, Freddy Estefano (2312081)
 */
public class ClienteController {
    private final ClienteRepository clienteRepo;
    private final MascotaRepository mascotaRepo;
    private final AuditoriaLogger auditoria;

    public ClienteController() {
        this.clienteRepo = new ClienteRepository();
        this.mascotaRepo = new MascotaRepository();
        this.auditoria = AuditoriaLogger.getInstancia();
    }

    public Cliente registrar(Cliente c) {
        ValidadorDNI.validar(c.getDni());
        clienteRepo.guardar(c);
        log("INSERT", "clientes", c.getId(), "Cliente registrado: " + c.getNombreCompleto());
        return c;
    }

    public Cliente buscarPorId(int id) {
        Cliente c = clienteRepo.buscarPorId(id).orElseThrow(() -> new ClienteNotFoundException("id=" + id));
        c.setMascotas(mascotaRepo.listarPorCliente(id));
        return c;
    }

    public List<Cliente> buscarPorNombre(String nombre) { return clienteRepo.buscarPorNombre(nombre); }

    public List<Cliente> listarTodos() { return clienteRepo.listarTodos(); }

    public void actualizar(Cliente c) {
        ValidadorDNI.validar(c.getDni());
        clienteRepo.actualizar(c);
        log("UPDATE", "clientes", c.getId(), "Cliente actualizado: " + c.getDni());
    }

    public void eliminar(int id) {
        clienteRepo.eliminar(id);
        log("DELETE", "clientes", id, "Cliente eliminado: id=" + id);
    }

    public Mascota registrarMascota(Mascota m) {
        mascotaRepo.guardar(m);
        log("INSERT", "mascotas", m.getId(), "Mascota registrada: " + m.getNombre());
        return m;
    }

    public List<Mascota> listarMascotas(int idCliente) { return mascotaRepo.listarPorCliente(idCliente); }

    private void log(String accion, String tabla, int id, String detalle) {
        int uid = SessionManager.getInstancia().getUsuarioActual() != null ? SessionManager.getInstancia().getUsuarioActual().getId() : 0;
        auditoria.registrar(uid, accion, tabla, id, detalle);
    }
}
