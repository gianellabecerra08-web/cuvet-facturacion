package cuvet.database;

import cuvet.model.Rol;
import cuvet.model.Usuario;
import cuvet.util.PasswordUtil;

/**
 * Inserta datos iniciales requeridos por la aplicación cuando la base está vacía.
 * Los usuarios se crean con el mismo algoritmo de hash que usa el login.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class DatabaseSeeder {
    private static final String PASSWORD_DEFAULT = "admin123";
    private DatabaseSeeder() {}
    public static void inicializar() {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        if (!usuarioRepo.existeUsername("admin")) {
            usuarioRepo.guardar(crearUsuario("admin", "Administrador CUVET", Rol.ADMINISTRADOR));
        }
        if (!usuarioRepo.existeUsername("recep1")) {
            usuarioRepo.guardar(crearUsuario("recep1", "Recepcionista Principal", Rol.RECEPCIONISTA));
        }
        if (!usuarioRepo.existeUsername("recep2")) {
            usuarioRepo.guardar(crearUsuario("recep2", "Recepcionista Secundaria", Rol.RECEPCIONISTA));
        }
        if (!usuarioRepo.existeUsername("vet1")) {
            usuarioRepo.guardar(crearUsuario("vet1", "Dr. Veterinario Principal", Rol.VETERINARIO));
        }
        if (!usuarioRepo.existeUsername("vet2")) {
            usuarioRepo.guardar(crearUsuario("vet2", "Dra. Veterinaria Secundaria", Rol.VETERINARIO));
        }
    }
    private static Usuario crearUsuario(String username, String nombre, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPasswordHash(PasswordUtil.hashear(PASSWORD_DEFAULT));
        usuario.setNombre(nombre);
        usuario.setRol(rol);
        usuario.setActivo(true);
        return usuario;
    }
}
